package com.myccnice.practice.manual.leetcode.vo;

import java.util.ArrayList;
import java.util.List;

public class NormalTreeNode<T> {

    private T value;

    private List<NormalTreeNode<T>> children;

    public NormalTreeNode() {

    }

    public NormalTreeNode(T value) {
        super();
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<NormalTreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<NormalTreeNode<T>> children) {
        this.children = children;
    }

    public NormalTreeNode<T> add(NormalTreeNode<T> e) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(e);
        return this;
    }
}
