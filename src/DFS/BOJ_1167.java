package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1167 (트리의 지름)
public class BOJ_1167 {

    static List<int[]>[] tree;
    static boolean[] visited;
    static int selected;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());
        tree = new ArrayList[V + 1];
        visited = new boolean[V + 1];

        for(int i = 1; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());

            while (true) {
                int node2 = Integer.parseInt(st.nextToken());
                if(node2 == -1) break;
                int dis = Integer.parseInt(st.nextToken());

                tree[node1].add(new int[]{node2, dis});
            }
        }

        dfs(1, 0); // 임의의 노드에서 가장 먼 노드 탐색
        dfs(selected, 0); // 선택된 노드에서 가장 먼 거리의 노드 탐색

        System.out.println(max);
    }

    private static void dfs(int curr, int dis) {

        visited[curr] = true;

        if(max < dis) {
            selected = curr;
            max = dis;
        }

        for(int[] next : tree[curr]) {
            int num = next[0];
            if(!visited[num]) {
                dfs(num, dis + next[1]);
            }
        }

        visited[curr] = false;
    }
}
