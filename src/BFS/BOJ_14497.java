package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14497 (주난의 난(難))
public class BOJ_14497 {
    static int N, M, ans;
    static Node start, end;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken()) - 1;
        int y1 = Integer.parseInt(st.nextToken()) - 1;
        int x2 = Integer.parseInt(st.nextToken()) - 1;
        int y2 = Integer.parseInt(st.nextToken()) - 1;
        start = new Node(x1, y1, 0);
        end = new Node(x2, y2, 0);

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                if(line.charAt(j) == '#') {
                    map[i][j] = 1;
                    continue;
                }

                map[i][j] = line.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cnt, o2.cnt));
        pq.add(start);
        visited[start.x][start.y] = true;

        while(!pq.isEmpty()) {
            Node curr = pq.poll();

            if(curr.x == end.x && curr.y == end.y) {
                ans = curr.cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

                if(map[nx][ny] == 0) {
                    pq.add(new Node(nx, ny, curr.cnt));
                } else {
                    pq.add(new Node(nx, ny, curr.cnt+1));
                }

                visited[nx][ny] = true;
            }
        }

    }

    private static class Node {
        int x;
        int y;
        int cnt;

        private Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
