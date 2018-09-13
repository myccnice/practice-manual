package com.myccnice.practice.manual.leetcode.algorithm.tree;

import com.myccnice.practice.manual.leetcode.vo.BinarySearchTreeNode;

/**
 * 二叉树
 * 1）性质：
 *  1、1 若任意节点的左子树不为空，则左子树上的节点均小于它的根节点的值；
 *  1、2 若任意节点的右子树不为空，则右子树上的节点均大于它的根节点的值；
 *  1、3 任意节点的左右子树也是二叉树；
 *  1、4 任意节点的值均不相等。
 *
 * create in 2018年9月13日
 * @author wangpeng
 */
public class BinarySearchTree {

    public static  void theFirstTraversal(BinarySearchTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        if (root.getLeftChild() != null) {
            theFirstTraversal(root.getLeftChild());
        }
        if (root.getRightChild() != null) {
            theFirstTraversal(root.getRightChild());
        }
    }
}