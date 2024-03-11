package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11058 (크리보드)
public class BOJ_11058 {
    static int N;
    static long[] dp, buffer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];
        buffer = new long[N+1];

        solution();

        System.out.println(dp[N]);
    }

    private static void solution() {
        for(int i = 1; i <= N; i++) {
            // max(A를 출력하는 경우, max(버퍼를 붙여 넣는 경우, 전체 선택 -> 버퍼에 복사 -> 버퍼를 붙여 넣는 경우))
            dp[i] = dp[i-1] + 1;

            if(i > 6) {
                for(int j = 3; j < 6; j++) {
                    dp[i] = Math.max(dp[i], dp[i-j] * (j-1));
                }
            }
        }
    }
}
