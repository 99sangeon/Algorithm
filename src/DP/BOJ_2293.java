package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2293 (동전 1)
public class BOJ_2293 {

    static int N, K;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        coins = new int[N+1];
        dp = new int[N+1][K+1];

        for(int i = 1; i <= N; i++) {
            coins[i] = Integer.parseInt(br.readLine());
            dp[i][0] = 1;
        }

        solution();

        System.out.println(dp[N][K]);
    }

    private static void solution() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-coins[i] >= 0) {
                    dp[i][j] += dp[i][j-coins[i]];
                }
            }
        }
    }
}
