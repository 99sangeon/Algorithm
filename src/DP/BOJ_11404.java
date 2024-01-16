package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11404 (플로이드)
public class BOJ_11404 {
    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dp = new int[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            // form -> to의 루트가 1개 이상 존재할 수도 있음
            if(dp[from][to] == 0) {
                dp[from][to] = dis;
            } else {
                dp[from][to] = Math.min(dp[from][to], dis);
            }
        }

        // 모든 노드 쌍에 대한 최소값 ->  제목에서 알 수 있든이 플로이드-워셜 알고리즘 사용
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) dp[i][j] = 0;
                sb.append(dp[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
