package com.myccnice.practice.manual.leetcode.vo;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {

    private T value;

    private List<TreeNode<T>> children;

    public TreeNode() {

    }

    public TreeNode(T value) {
        super();
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public TreeNode<T> add(TreeNode<T> e) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(e);
        return this;
    }
}
