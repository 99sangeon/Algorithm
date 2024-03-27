package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1949 (우수 마을)
public class BOJ_1949 {
    static int N;
    static int[] population;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        graph = new List[N+1];
        visited = new boolean[N+1];
        dp = new int[N+1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        solution(1);

        // '우수 마을'로 선정된 마을 주민 수의 총 합을 최대로 해야 한다.
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void solution(int num) {
        visited[num] = true;

        dp[num][0] = population[num];

        for(int next : graph[num]) {
            if(visited[next]) continue;

            solution(next);

            // num 마을이 우수 마을로 선정되면 모든 자식 마을은 우수마을로 선정 될 수 없기 때문에,
            // 즉, 자식 마을이 선정되지 않았을 때 맥시멈 인구수를 더해줌.
            dp[num][0] += dp[next][1];

            // num 마을이 우수 마을로 선정되지 않는 경우 부모 마을은 우수 마을로 이미 선정되었음.
            // 즉, num의 자식 마을은 우수 마을로 선정되었든 아니든 상관 없음.
            dp[num][1] += Math.max(dp[next][0], dp[next][1]);
        }
    }
}
