package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2533 (사회망 서비스(SNS))
public class BOJ_2533 {
    static int N;
    static List<Integer>[] tree;
    static int[][] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        tree = new List[N+1];
        dp = new int[N+1][2];
        visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            tree[parent].add(child);
            tree[child].add(parent);
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }

    private static void dfs(int parent) {
        visited[parent] = true;

        dp[parent][0] = 0;
        dp[parent][1] = 1;

        for(int child : tree[parent]) {
            if(visited[child]) continue;

            dfs(child);
            dp[parent][0] += dp[child][1];
            dp[parent][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}
