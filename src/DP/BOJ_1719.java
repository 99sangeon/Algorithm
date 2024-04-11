package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1719 (택배)
public class BOJ_1719 {
    static int N, M;
    static int[][] dp;
    static int[][] ans;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N+1][N+1];
        ans = new int[N+1][N+1];
        sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dp[a][b] = cost;
            dp[b][a] = cost;
            ans[a][b] = b;
            ans[b][a] = a;
        }

        solution();

        System.out.println(sb);
    }

    private static void solution() {
        // k를 경유해서 i -> j로 가는 모든 경우를 탐색함
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                if(k == i || dp[i][k] == 0) {
                    continue;
                }
                for(int j = 1; j <= N; j++) {
                    if(i == j || dp[k][j] == 0) {
                        continue;
                    }

                    int tempCost =  dp[i][k] + dp[k][j];
                    // i -> j로 가는 경로가 탐색되지 않았거나, k를 경유해서 가는 비용이 더 작다면,
                    // i -> j로 갈 때 첫 번재 경유지는 i -> k로 갈 때 첫 번째 경유지와 같음
                    if(dp[i][j] == 0 || dp[i][j] > tempCost) {
                        dp[i][j] = tempCost;
                        ans[i][j] = ans[i][k];
                    }
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(ans[i][j] == 0) {
                    sb.append("-");
                } else {
                    sb.append(ans[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
    }
}
