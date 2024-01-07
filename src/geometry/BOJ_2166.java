package geometry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2166 (다각형의 면적)
public class BOJ_2166 {

    static int N;
    static int[] x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        x = new int[N+1];
        y = new int[N+1];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        x[N] = x[0];
        y[N] = y[0];

        long sum1 = 0, sum2 = 0;

        // 신발끈 공식
        for(int i = 0; i < N; i++) {
            sum1 += (long)x[i] * y[i+1];
            sum2 += (long)x[i+1] * y[i];
        }

        // 신발끈 공식
        double ans = Math.abs(sum1 - sum2) / 2d;

        System.out.println(String.format("%.1f", ans));
    }
}
