package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13023 (ABCDE )
public class BOJ_13023 {
    static int N, M, ans;
    static List<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N];
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i = 0; i < N; i++) {
            dfs(i, 0);

            if(ans == 1) {
                break;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int num, int depth) {
        if(depth == 4) {
            ans = 1;
            return;
        }

        visited[num] = true;

        for (int next : graph[num]) {
            if(!visited[next]) {
                dfs(next, depth+1);
            }
        }

        visited[num] = false;
    }
}
