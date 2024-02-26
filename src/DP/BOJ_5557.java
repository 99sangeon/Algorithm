package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5557 (1학년)
public class BOJ_5557 {
    static int N, target;
    static int[] arr;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new long[N-1][21];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        target = arr[N-1];

        solution();

        System.out.println(dp[N-2][target]);
    }

    private static void solution() {
        dp[0][arr[0]] = 1;

        for(int i = 1; i < N-1; i++) {
            for(int j = 0; j <= 20; j++) {
                if(dp[i-1][j] == 0) continue;

                int sum = j + arr[i];
                int minus = j - arr[i];

                if(sum >= 0 && sum <= 20) {
                    dp[i][sum] += dp[i-1][j];
                }

                if(minus >= 0 && minus <= 20) {
                    dp[i][minus] += dp[i-1][j];
                }
            }
        }
    }
}
