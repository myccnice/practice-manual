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

    transient int size;

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

}
