package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/7579 (앱)
public class BOJ_7579 {

    static int N, M, ans = Integer.MAX_VALUE;
    static int[] byteArr;
    static int[] costArr;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        byteArr = new int[N+1];
        costArr = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            byteArr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int sum = 0;

        for(int i = 1; i <= N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());
            sum += costArr[i];
        }

        dp = new int[N+1][sum+1];

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < dp[i].length; j++) {

                if(j < costArr[i]) {
                    dp[i][j] = dp[i-1][j];
                    continue;
                }

                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-costArr[i]] + byteArr[i]);

                if(dp[i][j] >= M) ans = Math.min(ans, j);
            }
        }
    }
}
