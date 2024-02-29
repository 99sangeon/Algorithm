package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12869 (뮤탈리스트)
public class BOJ_12869 {
    static int N;
    static int[] arr;
    static int[][][] dp;
    static int[][] dmg = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[arr[0]+1][arr[1]+1][arr[2]+1];

        for(int i = 0; i < arr[0]+1; i++) {
            for(int j = 0; j < arr[1]+1; j++) {
                for(int k = 0; k < arr[2]+1; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;

        System.out.println(dfs(arr[0], arr[1], arr[2]));
    }

    private static int dfs(int fir, int sec, int thr) {
        // 음수라면 0이랑 동일함
        if(fir < 0) fir = 0;
        if(sec < 0) sec = 0;
        if(thr < 0) thr = 0;

        // 이미 알고 있는 값이라면
        if(dp[fir][sec][thr] != Integer.MAX_VALUE) {
            return dp[fir][sec][thr];
        }

        for(int i = 0; i < dmg.length; i++) {
            dp[fir][sec][thr] = Math.min(dp[fir][sec][thr], dfs(fir-dmg[i][0], sec-dmg[i][1], thr-dmg[i][2]) + 1);
        }

        return dp[fir][sec][thr];
    }

    private static boolean check() {
        for(int i = 0; i < N; i++) {
            if(arr[i] > 0) return false;
        }
        return true;
    }
}
