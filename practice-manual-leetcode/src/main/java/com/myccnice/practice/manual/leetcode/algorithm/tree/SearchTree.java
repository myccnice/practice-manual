package com.myccnice.practice.manual.leetcode.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

import com.myccnice.practice.manual.leetcode.vo.TreeNode;

/**
 * 遍历树的基本方法：1)深度优先、2)广度优先
 * @author chengmi
 */
public class SearchTree {

    /**
     * 深度优先:
     * 其过程简要来说是对每一个可能的分支路径深入到不能再深入为止。
     * 深度优先遍历各个节点，需要使用到栈（Stack）这种数据结构。
     * 首先将最顶级节点入栈，然后依次出栈。
     * 父节点出栈时需要将其子节点从右至左依次入栈（先遍历左子树）
     * @author chengmi
     */
    public static <T> List<TreeNode<T>> depthFirstSearch(TreeNode<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<TreeNode<T>> list = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            TreeNode<T> pop = stack.pop();
            list.add(pop);
            List<TreeNode<T>> children = pop.getChildren();
            if (children != null) {
                for (int i = children.size() -1; i >= 0; i--) {
                    stack.push(children.get(i));
                }
            }
        }
        return list;
    }

    /**
     * 广度优先:
     * 其过程检验来说是对每一层节点依次访问，访问完一层进入下一层。
     * 广度优先遍历各个节点，需要使用到队列（Queue）这种数据结构，queue的特点是先进先出，
     * 其实也可以使用双端队列，区别就是双端队列首尾都可以插入和弹出节点。
     */
    public static <T> List<TreeNode<T>> breadthFirstSearch(TreeNode<T> root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<TreeNode<T>> list = new ArrayList<>();
        Deque<TreeNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode<T> pop = queue.pop();
            list.add(pop);
            List<TreeNode<T>> children = pop.getChildren();
            if (children != null) {
                queue.addAll(children);
            }
        }
        return list;
    }
}
