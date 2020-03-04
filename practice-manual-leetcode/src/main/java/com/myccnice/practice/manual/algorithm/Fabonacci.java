package com.myccnice.practice.manual.algorithm;

public class Fabonacci {

    public static int fabonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n ==1) {
            return 1;
        }
        int[] cache = new int[n + 1];
        cache[0] = 1;
        cache[1] = 1;
        int sum = 0;
        for(int i = 2; i <= n; i++) {
            sum = cache[i -1] + cache[i - 2];
            cache[i] = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(fabonacci(2));
    }
}
