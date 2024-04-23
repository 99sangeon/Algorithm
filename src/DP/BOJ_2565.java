package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2565 (전깃줄)
public class BOJ_2565 {
    static int N;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        dp = new int[N+1];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[i][0] = a;
            arr[i][1] = b;
        }

        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        System.out.println(N - solution(0) + 1);
    }

    private static int solution(int idx) {
        if(dp[idx] != 0) {
            return dp[idx];
        }

        for(int i = idx+1; i <= N; i++) {
            if(arr[idx][1] < arr[i][1]) {
                dp[idx] = Math.max(dp[idx], solution(i));
            }
        }

        dp[idx]++;

        return dp[idx];
    }
}
