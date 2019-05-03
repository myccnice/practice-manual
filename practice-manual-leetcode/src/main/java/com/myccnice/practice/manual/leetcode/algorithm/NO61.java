package com.myccnice.practice.manual.leetcode.algorithm;

import java.util.Arrays;

import com.myccnice.practice.manual.leetcode.vo.ListNode;

public class NO61 {

    public ListNode rotateRight(ListNode head, int k) {
        int size = size(head);
        int offset = k % size;
        if (offset == 0) {
            return head;
        }
        ListNode temp = head;
        while(offset-- >0) {
            
        }
        return temp;
    }

    private int size(ListNode head) {
        int size = 0;
        ListNode temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
    
    public static void main(String[] args) {
        ListNode head = ListNode.of(Arrays.asList(1, 2, 3, 4, 5));
        NO61 no61 = new NO61();
        no61.rotateRight(head, 2);
    }
}
