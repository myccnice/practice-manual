package com.myccnice.practice.manual.datastructure.brtree;

public class Operation<K,V> {

    transient Entry<K,V> root = null;

    public void rotateLeft(Entry<K, V> p) {
        if (p == null) {
            return;
        }
        Entry<K,V> r = p.right;
        // 关联好右子树的左子树和当前节点的关系
        p.right = r.left;
        if (r.left != null) {
            r.left.parent = p;
        }
        // 关联好右子树和当前节点父节点的关系
        r.parent = p.parent;
        if (p.parent == null) {
            root = r;
        } else if (p.parent.right == p) {
            p.parent.right = r;
        } else {
            p.parent.left = r;
        }
        // 关联当前节点和右子树的关系
        r.left = p;
        p.parent = r;
    }

    public void rotateRight(Entry<K, V> p) {
        if (p == null) {
            return;
        }
        // 关联号左子树的右子树和当前节点的关系
        Entry<K, V> l = p.left;
        p.left = l.right;
        if (l.right != null) {
            l.right.parent = p;
        }
        // 关联好当前节点的左节点和父节点的关系
        l.parent = p.parent;
        if (p.parent == null) {
            root = l;
        } else if (p.parent.left == p) {
            p.parent.left = l;
        } else {
            p.parent.right = l;
        }
        // 关联好当前节点和左子树的 关系
        p.parent = l;
        l.right = p;
    }
}
