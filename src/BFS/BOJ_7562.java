package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7562(나이트의 이동)
public class BOJ_7562 {
    static int T, N;
    static boolean[][] visited;
    static StringBuilder sb;
    static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++){
            N = Integer.parseInt(br.readLine());
            visited = new boolean[N][N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            bfs(sx, sy, ex, ey);
        }

        System.out.println(sb);
    }

    private static void bfs(int sx, int sy, int ex, int ey) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int cx = poll.x;
            int cy = poll.y;

            if(cx == ex && cy == ey) {
                sb.append(poll.cost).append("\n");
                return;
            }

            for(int i = 0; i < dx.length; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >=N || visited[nx][ny]) continue;

                q.add(new Node(nx, ny, poll.cost + 1));
                visited[nx][ny] = true;
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
