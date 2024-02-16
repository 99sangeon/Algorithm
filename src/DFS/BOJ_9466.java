package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9466 (텀 프로젝트)
public class BOJ_9466 {
    static int T, N, res, ans;
    static int[] graph;
    static boolean[] visited, done;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N+1];
            visited = new boolean[N+1];
            done = new boolean[N+1];
            res = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= N; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
                if(i == graph[i]) visited[i] = true;
            }

            ans = solution();
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static int solution() {

        for(int i = 1; i <= N; i++) {
            dfs(i);
        }

        // 정답
        // 전체 인원 수 - 특정 그룹에 속한 인원
        return N - res;
    }

    private static void dfs(int num) {
        // 이미 탐색한 곳은 더이상 탐색하지 않음
        if(done[num]) return;

        // 방문한 곳을 또 방문함 -> 사이클
        if(visited[num]) {
            // 그룹에 속한 인원수 증가
            res++;
            // 스택이 무한으로 쌓이는 것을 방지하기 위하여 탐색 처리
            done[num] = true;
        } else {
            visited[num] = true;
        }

        dfs(graph[num]);

        visited[num] = false;
        done[num] = true;
    }
}
