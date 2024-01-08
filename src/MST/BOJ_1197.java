package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1197 (최소 스패닝 트리)
public class BOJ_1197 {

    static int V, E;
    static int[] parents;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        edges = new int[E][3];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            edges[i][0] = a;
            edges[i][1] = b;
            edges[i][2] = dis;
        }

        for(int i = 1; i < V; i++) {
            parents[i] = i;
        }

        Arrays.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        int cnt = 0;
        int ans = 0;

        // 크루스칼 알고리 -> 유니온 파인드
        for(int i = 0; i < E; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int dis = edges[i][2];

            if(find(a) != find(b)) {
                union(a, b);
                ans += dis;
                cnt++;
            }

            if(cnt == V - 1) break;
        }

        System.out.println(ans);

    }

    private static int find(int a) {
        if(parents[a] == a) {
            return a;
        }

        return find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) parents[b] = a;
        if(a > b) parents[a] = b;
    }
}
