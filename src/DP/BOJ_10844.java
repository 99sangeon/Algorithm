package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/10844 (쉬운 계단 수)
public class BOJ_10844 {
    static final int MOD = 1000000000;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N][10];

        int sum = 0;

        for(int i = 1; i < 10; i++) {
            sum += solution(0, i);
            sum %= MOD;
        }

        System.out.println(sum);
    }

    private static int solution(int depth, int num) {
        if(depth == N - 1) {
            dp[depth][num] = 1;
        }

        if(dp[depth][num] > 0) {
            return dp[depth][num];
        }

        if(num > 0) {
            dp[depth][num] += solution(depth+1, num-1);
        }

        if(num < 9) {
            dp[depth][num] += solution(depth+1, num+1);
        }

        dp[depth][num] %= MOD;

        return dp[depth][num];
    }
}
