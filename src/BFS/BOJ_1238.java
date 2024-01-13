package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1238 (파티)
public class BOJ_1238 {

    static int N, M, X, ans;
    static int[] distances;
    static List<Node>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        distances =new int[N+1];
        map = new List[N+1];

        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map[from].add(new Node(to, dis));
        }

        for(int i = 1; i <= N; i++) {
            // i번째 마을에서 X(목적지)까지 최단거리
            distances[i] = bfs(i);
        }

        solve();

        System.out.println(ans);
    }

    private static int bfs(int from) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dis, o2.dis));
        pq.add(new Node(from, 0));

        int distance = 0;
        boolean[] visited = new boolean[N+1];

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if(curr.node == X) {
                distance = curr.dis;
                break;
            }

            if(visited[curr.node]) continue;

            visited[curr.node] = true;

            for (Node next : map[curr.node]) {
                if(!visited[next.node]) {
                    pq.add(new Node(next.node, curr.dis + next.dis));
                }
            }
        }

        return distance;
    }

    private static void solve() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dis, o2.dis));
        pq.add(new Node(X, 0));

        boolean[] visited = new boolean[N+1];

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if(visited[curr.node]) continue;

            visited[curr.node] = true;

            // 특정 마을에서 X 마을까지의 최단거리 + X 마을에서 특정 마을까지의 최단거리
            int temp = distances[curr.node] + curr.dis;
            ans = Math.max(ans, temp);

            for (Node next : map[curr.node]) {
                if(!visited[next.node]) {
                    pq.add(new Node(next.node, curr.dis + next.dis));
                }
            }
        }
    }

    private static class Node {
        int node;
        int dis;

        public Node(int node, int dis) {
            this.node = node;
            this.dis = dis;
        }
    }

}
