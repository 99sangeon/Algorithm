package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11501 (주식)
public class BOJ_11501 {
    static int T, N;
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            long ans = solution();
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static long solution() {
        long ans = 0;

        int max = 0;

        for(int i = N-1; i >= 0; i--) {
            if(arr[i] > max) {
                max = arr[i];
            } else {
                ans += max - arr[i];
            }
        }

        return ans;
    }
}
