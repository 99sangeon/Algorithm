package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2240 (자두나무)
public class BOJ_2240 {

    static int T, W, ans;
    static int[] arr;
    // [시간][움직인 횟수][위치]
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[T+1];
        dp = new int[T+1][W+1][2];

        for(int i = 1; i <= T; i++) {
            arr[i] = Integer.parseInt(br.readLine()) - 1;

            for(int j = 0; j < W+1; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        arr[0] = -1;
        dp[0][0][0] = -1;
        solution(0, 0, 0);

        ans = dp[0][0][0];

        System.out.println(ans);
    }

    private static int solution(int t, int w, int n) {
        if(t > T) return 0;

        // 이미 값을 갱신한 적 있다면 별도의 과정 없이 값을 즉시 반환(이 부분이 없다면 시간초과 발생)
        if(dp[t][w][n] > -1) return dp[t][w][n];

        if(w < W) {  // 이동 횟수가 남아서 더 이동할 수 있음
            dp[t][w][n] = Math.max(solution(t+1, w, n), solution(t+1, w+1, (n+1)%2));
        } else {     // 이동 횟수를 다 사용해서 더이상 이동할 수 없음
            dp[t][w][n] = solution(t+1, w, n);
        }

        // 해당 시간에 해당 위치로 자두가 떨어져서 자두를 먹을 수 있음
        if(arr[t] == n) dp[t][w][n]++;

        return dp[t][w][n];
    }
}
