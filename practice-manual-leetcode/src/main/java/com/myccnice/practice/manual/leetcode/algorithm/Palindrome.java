package com.myccnice.practice.manual.leetcode.algorithm;

public class Palindrome {
    public boolean isPalindrome(int x) {
        int temp = x;
        if (temp < 0) {
            return false;
        }
        int rever = 0;
        while (temp > rever) {
            rever = rever * 10 + temp % 10;
            temp /= 10;
        }
        return temp == rever || temp == rever / 10;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        System.out.println(p.isPalindrome(121));
    }
}
