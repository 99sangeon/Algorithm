package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1600 (말이 되고픈 원숭이)
public class BOJ_1600 {
    static int K, W, H, ans = -1;
    static int[][] map;
    static boolean[][][] visited;
    // 원숭이의 이동 방향
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 말의 이동 방향
    static int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hy = {-1, 1, -2, 2, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        visited = new boolean[H][W][K+1];

        for(int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, K));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            int cx = poll.x;
            int cy = poll.y;

            if(cx == H-1 && cy == W-1) {
                ans = poll.move;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1 || visited[nx][ny][poll.remainingK]) continue;

                q.add(new Node(nx, ny, poll.move+1, poll.remainingK));
                visited[nx][ny][poll.remainingK] = true;
            }

            if(poll.remainingK > 0) {
                for(int i = 0; i < 8; i++) {
                    int nx = cx + hx[i];
                    int ny = cy + hy[i];

                    if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] == 1 || visited[nx][ny][poll.remainingK-1]) continue;

                    q.add(new Node(nx, ny, poll.move+1, poll.remainingK-1));
                    visited[nx][ny][poll.remainingK-1] = true;
                }
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int move;
        int remainingK;

        public Node(int x, int y, int move, int remainingK) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.remainingK = remainingK;
        }
    }
}
