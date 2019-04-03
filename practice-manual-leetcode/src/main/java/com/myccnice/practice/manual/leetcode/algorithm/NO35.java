package com.myccnice.practice.manual.leetcode.algorithm;

public class NO35 {
    public static int searchInsert(int[] nums, int target) {
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] >= target) {
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};
        System.out.println(searchInsert(nums, 0));
    }
}
