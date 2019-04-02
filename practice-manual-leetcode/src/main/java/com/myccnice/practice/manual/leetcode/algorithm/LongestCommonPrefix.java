package com.myccnice.practice.manual.leetcode.algorithm;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String comm = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            int size = Math.min(comm.length(), strs[i].length());
            if (size == 0) {
                return "";
            }
            int j = 0;
            for (; j < size; j++) {
                if (str.charAt(j) != comm.charAt(j)) {
                    break;
                }
            }
            comm = comm.substring(0, j);
        }
        return comm;
    }
}
