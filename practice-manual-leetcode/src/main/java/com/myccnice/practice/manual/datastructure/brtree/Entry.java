package com.myccnice.practice.manual.datastructure.brtree;

/**
 * 红黑树节点
 *
 * @author 王鹏
 * @date 2019年4月4日
 */
public class Entry<K, V> {

    public static final boolean BLACK = Boolean.TRUE;
    public static final boolean RED = Boolean.FALSE;

    K key;
    V value;

    Entry<K, V> left;
    Entry<K, V> right;
    Entry<K, V> parent;

    boolean color = BLACK;

    Entry(K key, V value, Entry<K,V> parent) {
        this.key = key;
        this.value = value;
        this.parent = parent;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V old = this.value;
        this.value = value;
        return old;
    }

    public String toString() {
        return String.format("{%s:%s->%s}", color, key, value);
    }
}
