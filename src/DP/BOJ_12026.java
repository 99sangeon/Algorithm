package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/12026 (BOJ 거리)
public class BOJ_12026 {
    static int N;
    static char[] map;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = br.readLine().toCharArray();
        dp = new int[N];

        Arrays.fill(dp, Integer.MAX_VALUE);

        solution();

        int ans = dp[N-1];
        if(ans == Integer.MAX_VALUE) ans = - 1;

        System.out.println(ans);
    }

    private static void solution() {
        dp[0] = 0;

        for(int i = 0; i < N; i++) {
            if(dp[i] == Integer.MAX_VALUE) continue;

            char curr = map[i];

            for(int j = i+1; j < N; j++) {
                char next = map[j];

                if((curr == 'B' && next == 'O') || (curr == 'O' && next == 'J') || (curr == 'J' && next == 'B')) {
                    dp[j] = Math.min(dp[j], dp[i] + ((j - i) * (j - i)));
                }
            }
        }
    }
}
