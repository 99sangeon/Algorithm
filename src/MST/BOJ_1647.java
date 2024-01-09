package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1647 (도시 분할 계획)
public class BOJ_1647 {

    static int N, M;
    static int[] parents;
    static int[][] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N+1];
        edges = new int[M][3];

        for(int i = 1; i < N+1; i++) {
            parents[i] = i;
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[i][0] = Integer.parseInt(st.nextToken());
            edges[i][1] = Integer.parseInt(st.nextToken());
            edges[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(edges, (o1, o2) -> Integer.compare(o1[2], o2[2]));

        int MST = 0;
        int max = 0;
        int cnt = 0;

        for(int i = 0; i < M; i++) {
            int a = edges[i][0];
            int b = edges[i][1];
            int cost = edges[i][2];

            if(find(a) == find(b)) continue;

            union(a, b);

            MST += cost;
            max = cost;
            cnt++;

            if(cnt == N - 1) break;
        }

        // 크루스칼 알고리즘을 통해 최소 스패닝 트리를 찾음 -> 최소 스패닝 트리에서 가장 큰 비용의 길을 제거함
        int ans = MST - max;

        System.out.println(ans);
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) parents[b] = a;
        else if(a > b) parents[a] = b;
    }
}
