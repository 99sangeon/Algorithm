package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17404 (RGB거리 2)
public class BOJ_17404 {

    static int N;
    static int[][] colors, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        colors = new int[N+1][3];
        dp = new int[N+1][3];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            colors[i][0] = red;
            colors[i][1] = green;
            colors[i][2] = blue;
        }

        int ans = Integer.MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            // 첫 번째 집을 r, g, b 순으로 색칠
            for (int j = 0; j < 3; j++) {
                if(i == j) dp[1][j] = colors[1][j];
                else dp[1][j] = 1001;
            }

            for(int k = 2; k <= N; k++) {

                int red = colors[k][0];
                int green = colors[k][1];
                int blue = colors[k][2];


                dp[k][0] = Math.min(dp[k-1][1], dp[k-1][2]) + red;
                dp[k][1] = Math.min(dp[k-1][0], dp[k-1][2]) + green;
                dp[k][2] = Math.min(dp[k-1][0], dp[k-1][1]) + blue;

                if(k == N) {
                    // 첫 번째 집이 빨강일 겅우
                    if(i == 0) {
                        ans = Math.min(ans, Math.min(dp[N][1], dp[N][2]));
                    }
                    // 첫 번째 집이 초록일 겅우
                    if(i == 1) {
                        ans = Math.min(ans, Math.min(dp[N][0], dp[N][2]));
                    }
                    // 첫 번째 집이 파랑 겅우
                    if(i == 2) {
                        ans = Math.min(ans, Math.min(dp[N][0], dp[N][1]));
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
