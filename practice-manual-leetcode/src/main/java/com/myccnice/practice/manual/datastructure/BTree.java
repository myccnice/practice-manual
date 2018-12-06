package com.myccnice.practice.manual.datastructure;

/**
 * 平衡多路查找树
 * 为了描述B-Tree，首先定义一条记录为一个二元组[key, data] ，key为记录的键值，对应表中的主键值，data为一行记录中除主键外的数据。对于不同的记录，key值互不相同。
 * 一棵m阶的B-Tree有如下特性： 那么这个 m 阶是怎么定义的呢？这里我们以一个节点能拥有的最大子节点数来表示这颗树的阶数。
 * 举个例子，如果一个节点最多有 n 个key，那么这个节点最多就会有 n+1 个子节点，这棵树就叫做 n+1（m=n+1）阶树。
 * 1. 每个节点最多有m个孩子。 
 * 2. 除了根节点和叶子节点外，其它每个节点至少有Ceil(m/2)个孩子。 
 * 3. 若根节点不是叶子节点，则至少有2个孩子 
 * 4. 所有叶子节点都在同一层，且不包含其它关键字信息 
 * 5. 每个非终端节点包含n个关键字信息（P0,P1,…Pn, k1,…kn） 
 * 6. 关键字的个数n满足：ceil(m/2)-1 <= n <= m-1 
 * 7. ki(i=1,…n)为关键字，且关键字升序排序。 
 * 8. Pi(i=1,…n)为指向子树根节点的指针。P(i-1)指向的子树的所有节点关键字均小于ki，但都大于k(i-1)
 *
 * @author 王鹏
 * @date 2018年12月6日
 */
public class BTree<Key extends Comparable<Key>, Data> {
    // max children per B-tree node = M-1
    // (must be even and greater than 2)
    private static final int M = 4;

    private Node root;       // root of the B-tree
    private int height;      // height of the B-tree
    private int n;           // number of key-value pairs in the B-tree

    // helper B-tree node data type
    private static final class Node {
        private int m;                             // 子节点个数
        private Entry[] children = new Entry[M];   // the array of children

        // create a node with k children
        private Node(int k) {
            m = k;
        }
    }

    // internal nodes: only use key and next
    // external nodes: only use key and value
    // 内部节点只有key和next
    private static class Entry {
        private Comparable key;
        private Object val;
        private Node next;     // helper field to iterate over array entries
        public Entry(Comparable key, Object val, Node next) {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty B-tree.
     */
    public BTree()  {
        root = new Node(0);
    }
 
    /**
     * Returns true if this symbol table is empty.
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns the height of this B-tree (for debugging).
     *
     * @return the height of this B-tree
     */
    public int height() {
        return height;
    }


    /**
     * Returns the value associated with the given key.
     *
     * @param  key the key
     * @return the value associated with the given key if the key is in the symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public Data get(Key key) {
        if (key == null){
            throw new NullPointerException("key must not be null");
        }
        return search(root, key, height);
    }

    private Data search(Node x, Key key, int ht) {
        Entry[] children = x.children;

        // 叶子节点
        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) {
                    return (Data) children[j].val;
                }
            }
        } else  { // 非叶子节点
            for (int j = 0; j < x.m; j++) {
                if (j+1 == x.m || less(key, children[j+1].key)) {
                    return search(children[j].next, key, ht-1);
                }
            }
        }
        return null;
    }


    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     *
     * @param  key the key
     * @param  val the value
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public void put(Key key, Data val) 
    {
        if (key == null) 
        {
            throw new NullPointerException("key must not be null");
        }
        Node u = insert(root, key, val, height); //分裂后生成的右结点
        n++;
        if (u == null) 
        {
            return;
        }

        // need to split root重组root
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private Node insert(Node h, Key key, Data val, int ht) 
    {
        int j;
        Entry t = new Entry(key, val, null);

        // external node外部结点，也是叶子结点，在树的最底层，存的是内容value
        if (ht == 0) 
        {
            for (j = 0; j < h.m; j++) 
            {
                if (less(key, h.children[j].key)) 
                {
                    break;
                }
            }
        }

        // internal node内部结点，存的是next地址
        else 
        {
            for (j = 0; j < h.m; j++) 
            {
                if ((j+1 == h.m) || less(key, h.children[j+1].key)) 
                {
                    Node u = insert(h.children[j++].next, key, val, ht-1);
                    if (u == null) 
                    {
                        return null;
                    }
                    t.key = u.children[0].key;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--)
        {
            h.children[i] = h.children[i-1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) 
        {
            return null;
        }
        else         
        {   //分裂结点
            return split(h);
        }
    }

    // split node in half
    private Node split(Node h) 
    {
        Node t = new Node(M/2);
        h.m = M/2;
        for (int j = 0; j < M/2; j++)
        {
            t.children[j] = h.children[M/2+j];          
        }
        return t;    
    }

    /**
     * Returns a string representation of this B-tree (for debugging).
     *
     * @return a string representation of this B-tree.
     */
    public String toString() 
    {
        return toString(root, height, "") + "\n";
    }

    private String toString(Node h, int ht, String indent) 
    {
        StringBuilder s = new StringBuilder();
        Entry[] children = h.children;

        if (ht == 0) 
        {
            for (int j = 0; j < h.m; j++) 
            {
                s.append(indent + children[j].key + " " + children[j].val + "\n");
            }
        }
        else 
        {
            for (int j = 0; j < h.m; j++) 
            {
                if (j > 0) 
                {
                    s.append(indent + "(" + children[j].key + ")\n");
                }
                s.append(toString(children[j].next, ht-1, indent + "     "));
            }
        }
        return s.toString();
    }


    // comparison functions - make Comparable instead of Key to avoid casts
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }


    /**
     * Unit tests the {@code BTree} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) 
    {
        BTree<String, String> st = new BTree<String, String>();

        st.put("www.cs.princeton.edu", "128.112.136.12");
        st.put("www.cs.princeton.edu", "128.112.136.11");
        st.put("www.princeton.edu",    "128.112.128.15");
        st.put("www.yale.edu",         "130.132.143.21");
        st.put("www.simpsons.com",     "209.052.165.60");
        st.put("www.apple.com",        "17.112.152.32");
        st.put("www.amazon.com",       "207.171.182.16");
        st.put("www.ebay.com",         "66.135.192.87");
        st.put("www.cnn.com",          "64.236.16.20");
        st.put("www.google.com",       "216.239.41.99");
        st.put("www.nytimes.com",      "199.239.136.200");
        st.put("www.microsoft.com",    "207.126.99.140");
        st.put("www.dell.com",         "143.166.224.230");
        st.put("www.slashdot.org",     "66.35.250.151");
        st.put("www.espn.com",         "199.181.135.201");
        st.put("www.weather.com",      "63.111.66.11");
        st.put("www.yahoo.com",        "216.109.118.65");


        System.out.println("cs.princeton.edu:  " + st.get("www.cs.princeton.edu"));
        System.out.println("hardvardsucks.com: " + st.get("www.harvardsucks.com"));
        System.out.println("simpsons.com:      " + st.get("www.simpsons.com"));
        System.out.println("apple.com:         " + st.get("www.apple.com"));
        System.out.println("ebay.com:          " + st.get("www.ebay.com"));
        System.out.println("dell.com:          " + st.get("www.dell.com"));
        System.out.println();

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);
        System.out.println();
    }
}
