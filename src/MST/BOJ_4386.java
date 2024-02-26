package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4386 (별자리 만들기)
public class BOJ_4386 {
    static int N;
    static double ans;
    static Node[] stars;
    static List<Edge> edges;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stars = new Node[N];
        edges = new ArrayList<>();
        parent = new int[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            stars[i] = new Node(x, y);
            parent[i] = i;
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        // 모든 간선들을 길이와 함께 객체화 하여 리스트에 저장함
        for(int i = 0; i < N; i++) {
            for(int j = i+1; j < N; j++) {
                // 점과 점사이의 거리 공식
                double distance = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        // 간선의 길이를 기준으로 오름차순 정렬
        edges.sort((o1, o2) -> Double.compare(o1.distance, o2.distance));

        // 크루스칼 알고리즘을 이용해서 최소 신장트리의 길이를 찾음
        int cnt = 0;

        for(int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);

            if(find(edge.node1) == find(edge.node2)) continue;

            union(edge.node1, edge.node2);
            ans += edge.distance;
            cnt++;

            if(cnt == N - 1) return;
        }
    }

    private static int find(int node) {
        if(parent[node] == node) return node;

        return find(parent[node]);
    }

    private static void union(int node1, int node2) {
        node1 = find(node1);
        node2 = find(node2);

        if(node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }

    private static class Node {
        double x;
        double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge {
        int node1;
        int node2;
        double distance;

        public Edge(int node1, int node2, double distance) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = distance;
        }
    }
}
