package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2240 (자두나무)
public class BOJ_2240 {

    static int T, W, ans;
    static int[] arr;
    // [시간][움직인 횟수][위치]
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[T+1];
        dp = new int[T+1][W+1][2];

        for(int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;

            for(int j = 0; j < W+1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        arr[0] = -1;
        dp[0][0][0] = -1;
        solution(0, 0, 0);

        ans = dp[0][0][0];

        System.out.println(ans);
    }

    private static int solution(int t, int w, int n) {
        if(t > T) return 0;

        if(dp[t][w][n] > -1) return dp[t][w][n];

        if(w < W) {
            dp[t][w][n] = Math.max(solution(t+1, w, n), solution(t+1, w+1, (n+1)%2));
        } else {
            dp[t][w][n] = solution(t+1, w, n);
        }

        if(arr[t] == n) dp[t][w][n]++;

        return dp[t][w][n];
    }
}
