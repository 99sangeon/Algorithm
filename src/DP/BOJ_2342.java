package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2342 (Dance Dance Revolution)
public class BOJ_2342 {

    static int N, ans;
    static int[] locations;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.countTokens() - 1;
        locations = new int[N];
        dp = new int[5][5][N];

        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        ans = solution(0, 0, 0);

        System.out.println(ans);
    }

    // top-down
    private static int solution(int left, int right, int cnt) {
        if(cnt == N) return 0;

        if(dp[left][right][cnt] > -1) return dp[left][right][cnt];

        // cnt번째의 최소값은 cnt+1번째의 최소값 + cnt -> cnt+1로 가기위한 피로도
        dp[left][right][cnt] = Math.min(
                solution(locations[cnt], right, cnt+1) + getFatigue(left, locations[cnt]),
                solution(left, locations[cnt], cnt+1) + getFatigue(right, locations[cnt]));

        return dp[left][right][cnt];
    }

    private static int getFatigue(int from, int to) {
        if(from == 0) return 2;
        if(from == to) return 1;

        int diff = Math.abs(from - to);

        if(diff == 2) return 4;
        else return 3;
    }
}
