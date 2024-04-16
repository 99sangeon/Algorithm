package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11057 (오르막 수)
public class BOJ_11057 {
    static int N;
    static int[][] dp;
    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][10];

        int ans = 0;

        for(int i = 0; i < 10; i++) {
            ans += solution(0, i);
        }

        ans %= MOD;

        System.out.println(ans);
    }

    private static int solution(int digit, int num) {
        if(digit == N-1) {
            dp[digit][num] = 1;
        }

        if(dp[digit][num] != 0) {
            return dp[digit][num];
        }

        for(int i = num; i < 10; i++) {
            dp[digit][num] += solution(digit+1, i);
        }

        dp[digit][num] %= MOD;

        return dp[digit][num];
    }
}
