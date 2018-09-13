package com.myccnice.practice.manual.leetcode.algorithm.tree;

import java.util.List;

import org.junit.Test;

import com.myccnice.practice.manual.leetcode.vo.NormalTreeNode;

public class SearchTreeTest {

    private NormalTreeNode<String> create() {
        NormalTreeNode<String> root = new NormalTreeNode<String>("A")
                .add(new NormalTreeNode<String>("B")
                        .add(new NormalTreeNode<String>("D")).add(new NormalTreeNode<String>("E")
                                .add(new NormalTreeNode<String>("I"))))
                .add(new NormalTreeNode<String>("C")
                        .add(new NormalTreeNode<String>("F"))
                        .add(new NormalTreeNode<String>("G"))
                        .add(new NormalTreeNode<String>("H")));
        return root;
    }

    @Test
    public void depthFirstSearch() {
        List<NormalTreeNode<String>> list = NormalSearchTree.depthFirstSearch(create());
        for (NormalTreeNode<String> node : list) {
            System.out.println(node.getValue());
        }
    }

    @Test
    public void breadthFirstSearch() {
        List<NormalTreeNode<String>> list = NormalSearchTree.breadthFirstSearch(create());
        for (NormalTreeNode<String> node : list) {
            System.out.println(node.getValue());
        }
    }
}
