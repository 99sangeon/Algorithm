package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1495 (기타리스트)
public class BOJ_1495 {
    static int N, S, M;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        dp = new boolean[N+1][M+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, S);

        int ans = getAns();

        System.out.println(ans);
    }

    private static void dfs(int depth, int volume) {
        if(volume < 0 || volume > M || dp[depth][volume]) {
            return;
        }

        dp[depth][volume] = true;

        if(depth >= N) {
            return;
        }

        dfs(depth+1, volume + arr[depth+1]);
        dfs(depth+1, volume - arr[depth+1]);
    }

    private static int getAns() {
        int ans = -1;

        for(int i = 0; i <= M; i++) {
            if(dp[N][i]) {
                ans = i;
            }
        }

        return ans;
    }
}
