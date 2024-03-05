package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1535 (안녕)
public class BOJ_1535 {
    static int N, ans;
    static int[] L, J;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        L = new int[N];
        J = new int[N];

        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            L[i] = Integer.parseInt(st1.nextToken());
            J[i] = Integer.parseInt(st2.nextToken());
        }

        comb(100, 0, 0);

        System.out.println(ans);
    }

    private static void comb(int stamina, int happy, int idx) {
        if(stamina <= 0) return;

        if(idx >= N) {
            ans = Math.max(ans, happy);
            return;
        }

        comb(stamina - L[idx], happy + J[idx], idx + 1);
        comb(stamina, happy, idx + 1);
    }
}
