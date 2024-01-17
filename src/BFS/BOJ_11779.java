package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11779 (최소비용 구하기 2)
public class BOJ_11779 {

    static int N, M, start, end;
    static boolean[] visited;
    static List<Node>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N+1];
        map = new List[N+1];

        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            map[from].add(new Node(to, dis, null));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // start -> end 최단거리
        Node node = dijkstra(start, end);

        StringBuilder sb = new StringBuilder();

        // 최단거리
        sb.append(node.dis).append("\n");

        List<Integer> route = new ArrayList<>();

        while (node != null) {
            route.add(node.num);
            node = node.pre;
        }

        // 경로에 포함되어있는 도시의 개수
        sb.append(route.size()).append("\n");

        // 경로
        for(int i = route.size() - 1; i >= 0; i--) {
            sb.append(route.get(i)).append(" ");
        }

        System.out.println(sb);
    }

    private static Node dijkstra(int start, int end) {

        Node node = new Node(start, 0, null);

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.dis, o2.dis));
        pq.add(new Node(start, 0, null));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if(curr.num == end) return curr;

            if(visited[curr.num]) continue;

            visited[curr.num] = true;

            for (Node next : map[curr.num]) {
                if(visited[next.num]) continue;
                pq.add(new Node(next.num, curr.dis + next.dis, curr));
            }
        }

        return node;
    }

    private static class Node {
        int num;
        int dis;
        Node pre;

        public Node(int num, int dis, Node pre) {
            this.num = num;
            this.dis = dis;
            this.pre = pre;
        }
    }
}
