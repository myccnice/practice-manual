package com.myccnice.practice.manual.leetcode;

/**
 * Hello world!
 *
 */
public class App {

    int[][] memo = new int[100][100];

    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int[] temp = new int[n];
        // 从第一行开始
        int size = n -1;
        // 右下角的数字
        int lowerRightCorner = 0;
        for (int i = 0; i < size; i++) {
            int[] current = A[i];
            lowerRightCorner = 0;
            for (int j = 0; j < size; j++) {
                int min = Math.min(temp[j], temp[j + 1]);
                temp[j] = min + current[j];
                temp[j + 1] = min + current[j + 1];
                lowerRightCorner = current[j + 1];
            }
        }
        return 0;
    }

    public int minFallingPathSum2(int[][] A) {
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100;j++){
                memo[i][j] = Integer.MIN_VALUE; 
            }
        }
        for(int i = 0; i < A[0].length; i++){
            int a = bfs(0,i,A,A[0][i]);
            if(res > a){
                res = a;
            }
        }
        return res;
    }

    public int bfs(int x,int y, int[][] A,int min){
        if(y <0  || y >=A[0].length){
            return Integer.MAX_VALUE;
        }
        if(memo[x][y] != Integer.MIN_VALUE){
            return memo[x][y];
        }
        if(x == A.length - 1){
            return A[x][y];
        }

        int a = bfs(x+1, y-1,A,min), b =bfs(x+1,y,A,min),c = bfs(x+1,y+1,A,min);
        int test = (a>b)?b : a;
        test = (test > c )?c:test;
        if(memo[x][y] == Integer.MIN_VALUE||(test + A[x][y]) < memo[x][y]){
            memo[x][y] = test + A[x][y];
        }
        min = memo[x][y];
        return min;
    }
}
