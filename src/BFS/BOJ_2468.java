package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2468 (안전 영역)
public class BOJ_2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i ++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        // 높이가 0일 때 잠기지 않는 영역의 개수는 1임(최소값)
        int ans = 1;

        // 1~100의 모든 비의양을 완전탐색
        for(int h = 1; h <= 100; h++) {
            int cnt = 0;
            visited = new boolean[N][N];

            // bfs 알고리즘을 통해 비의 양이 h일때 잠기지 않는 영역의 개수를 구함
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited[i][j] && map[i][j] > h) {
                        bfs(i, j, h);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }

        return ans;
    }

    private static void bfs(int x, int y, int height) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] <= height) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }
}
