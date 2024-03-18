package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16197 (두 동전)
public class BOJ_16197 {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];
        Queue<Location> q = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'o') {
                    if(q.isEmpty()) {
                        q.add(new Location());
                        q.peek().x1 = i;
                        q.peek().y1 = j;
                    } else {
                        q.peek().x2 = i;
                        q.peek().y2 = j;
                    }
                }
            }
        }

        int ans = bfs(q);

        System.out.println(ans);
    }

    private static int bfs(Queue<Location> q) {
        visited[q.peek().x1][q.peek().y1][q.peek().x2][q.peek().y2] = true;

        while(!q.isEmpty()) {
            Location curr = q.poll();

            if(curr.move >= 10) {
                return -1;
            }

            for(int i = 0; i < 4; i++) {
                // 코인 이동
                int nx1 = curr.x1 + dx[i];
                int ny1 = curr.y1 + dy[i];
                int nx2 = curr.x2 + dx[i];
                int ny2 = curr.y2 + dy[i];

                // 동전이 두 개 다 떨어진 경우
                if((nx1 >= N || ny1 >= M || nx1 < 0 || ny1  < 0) && (nx2 >= N || ny2 >= M || nx2 < 0 || ny2 < 0)) {
                    continue;
                }

                // 두 동전 중 하나만 떨어진 경우
                if((nx1 >= N || ny1 >= M || nx1 < 0 || ny1  < 0) || (nx2 >= N || ny2 >= M || nx2 < 0 || ny2 < 0)) {
                    return curr.move + 1;
                }

                // 동전1의 이동 위치 벽
                if(map[nx1][ny1] == '#') {
                    nx1 = curr.x1;
                    ny1 = curr.y1;
                }

                // 동전2의 이동 위치 벽
                if(map[nx2][ny2] == '#') {
                    nx2 = curr.x2;
                    ny2 = curr.y2;
                }

                if(visited[nx1][ny1][nx2][ny2]) {
                    continue;
                }

                q.add(new Location(nx1, ny1, nx2, ny2, curr.move + 1));
                visited[nx1][ny1][nx2][ny2] = true;
            }
        }

        return -1;
    }

    public static class Location {
        int x1;
        int y1;
        int x2;
        int y2;
        int move;

        public Location() {
        }

        public Location(int x1, int y1, int x2, int y2, int move) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.move = move;
        }
    }
}
