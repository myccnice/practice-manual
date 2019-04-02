package com.myccnice.practice.manual.leetcode.algorithm;

/**
 * https://leetcode-cn.com/problems/longest-palindromic-substring/comments/
 * @author chengmi
 *
 */
public class LongestPalindrome {
    public char[] manageString(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < s.length();i++) {
            sb.append("#");
            sb.append(s.charAt(i));
        }
        sb.append("#");

        return sb.toString().toCharArray();     
    }
    public String getTheString(char[] charArray, int maxRadius,int maxC) {
        //字符数组中的奇数才是所需要的字符
        StringBuilder sb = new StringBuilder();
        for(int i = maxC - maxRadius + 1; i < maxC + maxRadius ;i++) {
            if (i%2==1) {
                sb.append(charArray[i]);
            }
        }   
        return sb.toString();
    }
    public String longestPalindrome(String s) {
        //若为空直接返回
        if (s == "")return "";
        char[] strArray = manageString(s);
        int[] radius = new int[strArray.length];//回文半径数组
        int R = -1;//最右回文 的 右边界R
        int C = -1;//最右回文 的 对称中心C

        int maxRadius = 0;
        int maxC = -1;
        int length = strArray.length;

        for(int index = 0; index < length; index++) {
            if (index > R) {
                C = index;
                int j = 1;
                //要加一个上下界判断
                while( (index+j)<=length -1 && (index-j)>=0 && strArray[index-j] == strArray[index+j]) {
                    j++;
                }
                radius[index] = j;//更新半径数组
                R = C + j -1;//更新右边界R
            }else {
                //剩下的就是下一位置在R左边（或等于R），分三种情况处理
                int L = 2*C - R;//最右回文 的 左边界L
                int p2 = 2*C - index;//当前位置 关于对称中心C 的 对称点p2
                int p2L = p2 - radius[p2] +1;//以p2点为中心的 回文 的左边界p2L

                if(p2L > L) {
                    //第一种情况
                    radius[index] = radius[p2];
                }else if(p2L < L) {
                    //第二种情况
                    radius[index] = R - index + 1;
                }else {
                    //第三种情况（相等）
                    //以当前位置index为中心开始扩充
                    C = index;
                    int j = R - index + 1;
                    while( (index+j)<=length -1 && (index-j)>=0 && strArray[index-j] == strArray[index+j]) {
                        j++;
                    }
                    radius[index] = j;//更新半径数组
                    R = C + j -1;//更新右边界R               
                }
            }
            if(radius[index]>maxRadius) {
                maxRadius = radius[index];
                maxC = C;
            }
        }
        return getTheString(strArray,maxRadius,maxC);
    }
    
    public static void main(String[] args) {
        LongestPalindrome lp = new LongestPalindrome();
        System.out.println(lp.longestPalindrome("cbbd"));
    }
}
