package com.myccnice.practice.manual.leetcode.algorithm.tree;

import java.util.List;

import org.junit.Test;

import com.myccnice.practice.manual.leetcode.vo.TreeNode;

public class SearchTreeTest {

    private TreeNode<String> create() {
        TreeNode<String> root = new TreeNode<String>("A")
                .add(new TreeNode<String>("B")
                        .add(new TreeNode<String>("D")).add(new TreeNode<String>("E")
                                .add(new TreeNode<String>("I"))))
                .add(new TreeNode<String>("C")
                        .add(new TreeNode<String>("F"))
                        .add(new TreeNode<String>("G"))
                        .add(new TreeNode<String>("H")));
        return root;
    }

    @Test
    public void depthFirstSearch() {
        List<TreeNode<String>> list = SearchTree.depthFirstSearch(create());
        for (TreeNode<String> node : list) {
            System.out.println(node.getValue());
        }
    }

    @Test
    public void breadthFirstSearch() {
        List<TreeNode<String>> list = SearchTree.breadthFirstSearch(create());
        for (TreeNode<String> node : list) {
            System.out.println(node.getValue());
        }
    }
}
