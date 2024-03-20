package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1194 (달이 차오른다, 가자.)
public class BOJ_1194 {
    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][64];

        int sx = 0;
        int sy = 0;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == '0') {
                    sx = i;
                    sy = j;
                }
            }
        }

        int ans = bfs(sx, sy);

        System.out.println(ans);
    }

    private static int bfs(int sx, int sy) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy, 0, 0));
        visited[sx][sy][0] = true;

        while(!q.isEmpty()) {
            Node curr = q.poll();
            int nMove = curr.move + 1;

            if(map[curr.x][curr.y] == '1') return curr.move;

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny][curr.key] || map[nx][ny] == '#') continue;

                int nKey = curr.key;

                // 다음 칸에 열쇠가 있다면 비트마스킹을 이용해 다음 키 세팅
                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    nKey |= 1 << map[nx][ny] - 'a';
                }

                boolean flag = true;

                // 다음 칸에 문이 있다면 해당 문을 열 수 있는 키가 있는지 확인
                if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if((curr.key & (1 << map[nx][ny] - 'A')) == 0) {
                        flag = false;
                    }
                }

                if(flag) {
                    q.add(new Node(nx, ny, nKey, nMove));
                    visited[nx][ny][nKey] = true;
                }
            }
        }

        return -1;
    }

    private static class Node {
        int x;
        int y;
        int key;
        int move;

        public Node(int x, int y, int key, int move) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.move = move;
        }
    }
}
