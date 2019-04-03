package com.myccnice.practice.manual.leetcode.algorithm;

/**
 * https://leetcode-cn.com/problems/first-missing-positive/comments/
 * 首先要想到一个条件...也就是所缺失的正数一定是在这个数组的长度+1范围内的。
 * 比如一个长度为5的数组，最特殊的情况就是[1,2,3,4,5]，缺失的第一个正数是6，
 * 别的情况下，无论怎么填充数，所缺失的正数肯定是在[1,5]的范围内的。
 * @author chengmi
 *
 */
public class FirstMissingPositive {

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }
        int size = nums.length + 1;
        boolean[] mit = new boolean[size];
        for (int i : nums) {
            if (i > 0 && i < size) {
                mit[i] = true;
            }
        }
        int min = size;
        for (int i = 1; i < size; i++) {
            if (!mit[i]) {
                return i;
            }
        }
        return min;
    }
}
