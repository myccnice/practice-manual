package com.myccnice.practice.manual.leetcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class No78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>(1024);
        List<List<Integer>> temp = null;
        for (int num : nums) {
            temp = new ArrayList<>(result);
            for (List<Integer> list : temp) {
                list.add(num);
                result.add(new ArrayList<>(list));
            }
            result.add(new ArrayList<>(Arrays.asList(num)));
        }
        result.add(new ArrayList<>());
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        No78 no = new No78();
        List<List<Integer>> subsets = no.subsets(nums);
        for (List<Integer> list : subsets) {
            System.out.println(list);
        }
    }
}
