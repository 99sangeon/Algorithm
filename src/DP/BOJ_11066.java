package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11066 (파일 합치기)
public class BOJ_11066 {
    static int T, K;
    static int[] arr;
    static int[][] dp, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K+1];
            dp = new int[K+1][K+1];
            size = new int[K+1][K+1];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= K; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            for(int i = 1; i <= K; i++) {
                for(int j = i; j <= K; j++) {
                    size[i][j] = size[i][j-1] + arr[j];
                }
            }

            solution(1, K);

            sb.append(dp[1][K]).append("\n");
        }

        System.out.println(sb);
    }

    private static int solution(int from, int to) {
        if(from == to) {
            return 0;
        }

        if(dp[from][to] < Integer.MAX_VALUE) {
            return dp[from][to];
        }

        for(int i = from; i < to; i++) {
            int cost1 = solution(from, i);
            int cost2 = solution(i+1, to);
            int cost3 = size[from][i] + size[i+1][to];

            dp[from][to] = Math.min(dp[from][to], cost1 + cost2 + cost3);
        }

        return dp[from][to];
    }
}
