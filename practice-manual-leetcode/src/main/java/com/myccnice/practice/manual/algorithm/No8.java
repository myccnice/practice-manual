package com.myccnice.practice.manual.algorithm;

public class No8 {

    public static int autoi(String str) {
        // 1、忽略前面所有的空格，直接可以trim
        // 2、取出第0个判断符号，默认加
        // 3、从0-length遍历字符串 char
        // 3.1、如果取出的char在 '0'-'9'之间则转化为整数
        // 3.2、如果不在这之间直接返回
        str = (str == null) ? "" : str.trim();
        int length = str.length();
        if (length == 0) {
            return 0;
        }
        boolean positive = Boolean.TRUE;
        int i = 0;
        int sum = 0;
        if (str.charAt(0) == '-') {
            positive = Boolean.FALSE;
            i++;
        }
        for(; i < length; i++) {
            char c = str.charAt(i);
            if (c >= '0' && c <= '9') {
                int cur = c - 48;
                // 如果溢出
                if (overflow(sum, cur, positive)) {
                    return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                sum = sum * 10 + cur;
            } else {
                break;
            }
        }
        return sum > 0 ? (positive ? sum : -sum) : 0;
    }

    /**
     * 判断是否溢出
     * @param sum 前一阶段总和
     * @param cur 当前值
     * @param positive 是否正数
     * @return 是否溢出
     */
    private static boolean overflow(int sum, int cur, boolean positive) {
        if (!positive) {
            sum = -sum;
            cur = -cur;
        }
        if(sum > Integer.MAX_VALUE/10 || (sum == Integer.MAX_VALUE / 10 && cur > 7)){
            return true;
        }
        if(sum < Integer.MIN_VALUE/10 || (sum == Integer.MIN_VALUE / 10 && cur < -8)){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(autoi("   12344444444444444444444444444444444abcd"));
    }
}
