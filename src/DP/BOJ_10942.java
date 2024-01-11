package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10942 (팰린드롬?)
public class BOJ_10942 {
    static int N, M;
    static int[] arr;
    // dp[i][j] -> i ~ j 팰린드롬 여부
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new boolean[N+1][N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        solve();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            if(dp[S][E]) sb.append(1);
            else sb.append(0);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solve() {
        // 1개의 숫자 선택 시, 무조건 팰린드롬
        for(int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }

        // 2개의 숫자 선택 시, 두 숫자가 같으면 팰린드롬
        for(int i = 1; i < N; i++) {
            if(arr[i] == arr[i+1]) dp[i][i+1] = true;
        }

        // 3개 이상의 숫자 선택 시, i와 j는 같고 i+1 ~ j-1가 팰린드롬이면 팰린드롬
        for(int k = 2; k <= N; k++) {
            for(int i = 1; i <= N-k; i++) {
                int j = i + k;

                if(arr[i] == arr[j] && dp[i+1][j-1]) dp[i][j] = true;
            }
        }
    }
}
