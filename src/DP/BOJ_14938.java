package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14938 (서강그라운드)
public class BOJ_14938 {

    static int N, M, R, ans;
    static int[] items;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        items = new int[N+1];
        dp = new int[N+1][N+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());

            Arrays.fill(dp[i], Integer.MAX_VALUE);
            dp[i][i] = 0;
        }

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            dp[a][b] = dis;
            dp[b][a] = dis;
        }

        // 플로이드-워셜 알고리즘을 이용해 모든 노드 사이의 최단거리 탐색
        // V <= 100 -> V의 세제곱 = 1,000,000 -> 플로이드-워셜 알고리즘 사용 가능
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                if(i == k) continue;
                for(int j = 1; j <= N; j++) {
                    if(j == i) continue;
                    if(dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                    }
                }
            }
        }

        // 1~N 번중 착륙 -> 해당 지점에 착륙했을 때 예은이가 얻을 수 있는 최대 아이템 개수 탐색
        // i에 착륙, i -> j로 수색
        for(int i = 1; i <= N; i++) {
            int temp = 0;

            for(int j = 1; j <= N; j++){
                // i -> j까지 거리가 M이하 일때만 수색 가능
                if(dp[i][j] <= M) temp += items[j];
            }

            ans = Math.max(ans, temp);
        }

        System.out.println(ans);
    }
}
