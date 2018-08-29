package com.myccnice.practice.manual.leetcode.vo;

import java.util.Collections;
import java.util.List;

public class ListNode {

    public boolean cycle;

    public int val;

    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        if (cycle) {
            return val + "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(val);
        ListNode node = next;
        while (node != null) {
            sb.append("->").append(node.val);
            node = node.next;
        }
        sb.append(")");
        return sb.toString();
    }

    public static ListNode of(List<Integer> list) {
        ListNode head = new ListNode(0);
        Collections.reverse(list);
        for (Integer i : list) {
            ListNode temp = new ListNode(i);
            temp.next = head.next;
            head.next = temp;
        }
        return head.next;
    }
}
