package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1535 (안녕)
public class BOJ_1535 {
    static final int MAX = 100;
    static int N, ans;
    static int[] L, J;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        L = new int[N+1];
        J = new int[N+1];
        dp = new int[N+1][MAX];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            L[i] = Integer.parseInt(st1.nextToken());
            J[i] = Integer.parseInt(st2.nextToken());
        }

        solution();

        System.out.println(dp[N][MAX-1]);
    }

    private static void solution() {
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < MAX; j++) {
                if(j < L[i]) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-L[i]] + J[i]);
            }
        }
    }
}
