package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2206 (벽 부수고 이동하기)

public class BOJ_2206 {
    static int N, M;
    static int[][] map;
    static boolean[][][] visited;
    static int ans = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();

        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, false));
        visited[0][0][0] = true;
        visited[0][0][1] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            int cx = curr.x;
            int cy = curr.y;

            if(cx == N-1 && cy == M-1) {
                ans = curr.dis;
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                int dis = curr.dis + 1;

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(!curr.isBreak) { // 현재 위치까지 벽을 부수지 않음
                    if(map[nx][ny] == 0 && !visited[nx][ny][0]) {
                        visited[nx][ny][0] = true;
                        q.add(new Node(nx, ny, dis, false));
                    } else if (map[nx][ny] == 1 && !visited[nx][ny][1]) { // 다음 위치에 벽이 있을 때 부수고 이동 가능
                        visited[nx][ny][1] = true;
                        q.add(new Node(nx, ny, dis, true));
                    }

                } else {  // 현재 위치까지 벽을 하나 부숨
                    if(map[nx][ny] == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        q.add(new Node(nx, ny, dis, true));
                    }
                }
            }
        }
    }

    public static class Node {
        int x;
        int y;
        int dis;
        boolean isBreak;

        public Node(int x, int y, int dis, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.isBreak = isBreak;
        }
    }
}
