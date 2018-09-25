package com.myccnice.practice.manual.jdk.java_util;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 概念/术语：
 * 1）bin：哈希表的本质是一个数组，数组中每一个元素称为一个bin（散列桶）
 * 2）table：存放bin的数组（散列表）
 * 3）冲突（hash碰撞）:不同的key计算出相同的hash值。发生冲突的key称为该散列函数的同义词(synonym)。
 * 
 * 哈希表的存储过程如下:
 * 1)计算出key的hashcode->h
 * 2)假设table的长度length=n;那该值存放在下标为(h % n)的bin里
 * 3)如果bin里已经有了数据，则解决冲突(链表/树)
 *
 * create in 2018年9月14日
 * @author wangpeng
 */
public class MyHashMap<K, V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable  {

    private static final long serialVersionUID = 2146660321520016714L;

    /**
     * 数组的初始大小
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /**
     * 默认的负载因子,当键值对的数量大于 容量*负载因子就会进行扩容
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /**
     * 当发生hash碰撞时，我们使用拉链法进行同义词的冲突解决。但从JDK8后当链表太长时，转换成红黑树。
     * 这个值表示当bin中链表长度大于8时，有可能会转化成树。
     */
    static final int TREEIFY_THRESHOLD = 8;
    /**
     * 在哈希表扩容时，如果发现链表长度小于 6，则会由树重新退化为链表。
     */
    static final int UNTREEIFY_THRESHOLD = 6;
    /**
     * 链表转化为树时容量必须大于该值
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V newValue) {
            V old = value;
            value = newValue;
            return old;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 数组，bin是链表
     */
    transient Node<K,V>[] table;

    transient Set<Map.Entry<K,V>> entrySet;

    /**
     * 键值对的数量
     */
    transient int size;

    /**
     * 键值对被修改的次数
     */
    transient int modCount;

    int threshold;

    /**
     * 加载因子
     */
    final float loadFactor;

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public V get(Object key) {
        Node<K, V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }

    final Node<K,V> getNode(int hash, Object key) {
        Node<K,V>[] tab = table;
        int n = tab.length;
        Node<K,V> first = tab[(n - 1) & hash];
        Node<K,V> e = null;
        K k;
        if (tab != null && n > 0 && first != null) {
            if (first.hash == hash && ((k = first.key) == key || (key != null && key.equals(k))))
                return first;
            if ((e = first.next) != null) {
                do {
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        return e;
                } while ((e = e.next) != null);
            }
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return getNode(hash(key), key) != null;
    }

    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    /**
     * @param hash key的hash值
     * @param key key
     * @param value the value
     * @param onlyIfAbsent 是否只在不存在时添加，如果是则原来的值将不会改变
     * @param evict
     * @return
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        if ((p = tab[i = (n - 1) & hash]) == null) {
            tab[i] = newNode(hash, key, value, null);
        } else {
            Node<K,V> e; K k;
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else {
                for (; ;) {
                    // 如果链表的下个元素为空，则新建元素加入链表中
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        break;
                    }
                    // 判断是否当前元素为插入元素
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            // 该元素之前存在
            if (e != null) {
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }

    final Node<K,V>[] resize() {
        Node<K,V>[] oldTab = table;
        int oldCap = (oldTab == null) ? 0 : oldTab.length;
        // 历史阀值
        int oldThr = threshold;
        // 新的容量
        int newCap = 0;
        // 新的阀值
        int newThr = 0;
        if (oldCap > 0) {
            if (oldCap >= MAXIMUM_CAPACITY) {
                threshold = Integer.MAX_VALUE;
                return oldTab;
            }
            // 每次扩容一倍
            else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY && oldCap >= DEFAULT_INITIAL_CAPACITY) {
                // 阀值也扩容一倍
                newThr = oldThr << 1;
            }
        }
        // 第一次创建数组，并指定了阀值
        else if (oldThr > 0) {
            // 新生成的容量和阀值一直
            newCap = oldThr;
        }
        // 什么都没指定使用默认值
        else {
            newCap = DEFAULT_INITIAL_CAPACITY;
            // 扩容阀值是加载因子 * 容量
            newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
        }
        // 修正阀值
        if (newThr == 0) {
            float ft = (float)newCap * loadFactor;
            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ? (int)ft : Integer.MAX_VALUE);
        }
        threshold = newThr;
        @SuppressWarnings("unchecked")
        Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
        table = newTab;
        if (oldTab != null) {
            for (int j = 0; j < oldCap; ++j) {
                Node<K,V> e;
                if ((e = oldTab[j]) != null) {
                    oldTab[j] = null;
                    if (e.next == null)
                        newTab[e.hash & (newCap - 1)] = e;
                    else {
                        Node<K,V> loHead = null, loTail = null;
                        Node<K,V> hiHead = null, hiTail = null;
                        Node<K,V> next;
                        do {
                            next = e.next;
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null)
                                    loHead = e;
                                else
                                    loTail.next = e;
                                loTail = e;
                            }
                            else {
                                if (hiTail == null)
                                    hiHead = e;
                                else
                                    hiTail.next = e;
                                hiTail = e;
                            }
                        } while ((e = next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTab[j] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTab[j + oldCap] = hiHead;
                        }
                    }
                }
            }
        }
        return newTab;
    }

    Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
        return new Node<>(hash, key, value, next);
    }

    // Callbacks to allow LinkedHashMap post-actions
    void afterNodeAccess(Node<K,V> p) { }
    void afterNodeInsertion(boolean evict) { }
    void afterNodeRemoval(Node<K,V> p) { }

    public int size() {
        return size;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

}
