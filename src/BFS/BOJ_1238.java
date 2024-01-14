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
    static int[] distances, distances_reverse;
    static List<Node>[] map, map_reverse;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        distances = new int[N+1];
        distances_reverse = new int[N+1];
        map = new List[N+1];
        map_reverse = new List[N+1];

        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList();
            map_reverse[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map[from].add(new Node(to, dis));
            map_reverse[to].add(new Node(from, dis));
        }

        // 각각의 집에서 목적지까지 최단 거리
        bfs(distances_reverse, map_reverse);
        // 목적지에서 각각의 집까지 최단 거리
        bfs(distances, map);

        for(int i = 1; i <= N; i++) {
            ans = Math.max(ans, distances[i] + distances_reverse[i]);
        }

        System.out.println(ans);
    }

    private static void bfs(int[] distances, List<Node>[] map) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dis, o2.dis));
        pq.add(new Node(X, 0));

        boolean[] visited = new boolean[N+1];

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if(visited[curr.node]) continue;

            // 해당 위치의 노드 방문 처리 및 최단거리 세팅
            visited[curr.node] = true;
            distances[curr.node] = curr.dis;

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
