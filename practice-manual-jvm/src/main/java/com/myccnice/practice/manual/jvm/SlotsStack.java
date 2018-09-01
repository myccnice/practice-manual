package com.myccnice.practice.manual.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 可指定曹位置大小的栈。
 * 栈是一种“先进后出”的一种数据结构，有入栈(push)出(pop)栈两种操作方式
 *
 * create in 2018年8月31日
 * @author wangpeng
 */
public class SlotsStack<T> {

    private T[] buffer;

    private int end = 0;

    @SuppressWarnings("unchecked")
    public SlotsStack(int size) {
        this.buffer = (T[])new Object[size];
    }

    public void push(T entity) throws IllegalArgumentException{
        push(entity, 1);
    }

    public void push(T entity, int size) throws IllegalArgumentException {
        if(size <=0 || end + size > buffer.length){
            throw new IllegalArgumentException("invalid entity size "+size);
        }
        buffer[end] = entity;
        for (int i = 1; i < size; i++) {
            buffer[end + i] = null;
        }
        end += size;
    }

    /**
     * 出栈
     */
    public T pop() throws NoSuchElementException {
        while (end-- > 0) {
            T e = buffer[end];
            if (e != null) {
                buffer[end] = null;
                return e;
            }
        }
        throw new NoSuchElementException();
    }

    /**
     * 按栈内顺序 pop 出全部元素
     */
    @SuppressWarnings("unchecked")
    public T[] dumpAll() {
        List<T> list = new ArrayList<>();
        for (int begin = 0; begin < end; begin++){
            T entity = buffer[begin];
            if(entity != null){
                buffer[begin] = null;
                list.add(entity);
            }
        }
        return (T[]) list.toArray();
    }

    /**
     * 取出最后一个元素，但不出栈
     */
    public T pick(){
        int end = this.end;
        while (end-- > 0) {
            T e = buffer[end];
            if (e != null) {
                return e;
            }
        }
        return null;
    }
}
