package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2638 (치즈)
public class BOJ_2683 {

    static int N, M, ans;
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

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 외부 공기 영역 방문 처리
        bfs(0, 0);

        // 더이상 녹일 치즈가 없을때 까지 치즈 녹임
        while (meltCheese()) {
            ans++;
        }

        System.out.println(ans);

    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1 || visited[nx][ny]) continue;

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
    }

    private static boolean meltCheese() {
        List<Node> cheeses  = new ArrayList();

        for(int i = 1; i < N; i++) {
            for(int j = 1; j < M; j++) {
                int cnt = 0;

                if(map[i][j] == 1) {
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(visited[nx][ny]) cnt++;
                    }
                }

                // 해당 치즈를 녹일 수 있다면 리스트에 추가
                if(cnt >= 2) cheeses.add(new Node(i, j));
            }
        }

        if(cheeses.size() == 0) return false;

        for(Node cheese : cheeses) {
            // 해당 치즈 녹임
            map[cheese.x][cheese.y] = 0;
            // 해당 치즈가 녹았을 때 치즈 내부로 외부공기 유입
            bfs(cheese.x, cheese.y);
        }

        return true;
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
