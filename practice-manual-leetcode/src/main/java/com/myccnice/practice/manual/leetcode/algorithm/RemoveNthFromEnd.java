package com.myccnice.practice.manual.leetcode.algorithm;

import java.util.HashMap;
import java.util.Map;

import com.myccnice.practice.manual.leetcode.vo.ListNode;

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode next = head;
        Map<Integer, ListNode> indexMap = new HashMap<>();
        int index = 0;
        while(next != null) {
            indexMap.put(++index, next);
            next = next.next;
        }
        int order = index - n + 1;
        ListNode pre = indexMap.get(order -1);
        ListNode net = indexMap.get(order + 1);
        if (pre == null) {
            return net;
        }
        pre.next = net;
        return indexMap.get(1);
    }
}
