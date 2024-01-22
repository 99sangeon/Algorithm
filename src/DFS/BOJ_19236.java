package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19236 (청소년 상어)
public class BOJ_19236 {

    static int N = 4;
    static int ans;
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][][] map = new int[N][N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                map[i][j][0] = num;
                map[i][j][1] = dir;
            }
        }

        dfs(map, 0, 0, 0);

        System.out.println(ans);
    }

    private static void dfs(int[][][] map, int x, int y, int score) {
        // 상어가 해당 위치로 이동하고 물고기를 먹음
        score += map[x][y][0];
        map[x][y][0] = -1;
        // 상어 방향 설정
        int dir = map[x][y][1];

        ans = Integer.max(ans, score);

        // 모든 물고기들을 이동시킴
        move(map);

        // 현재 상어 위치 빈 공간으로 만듬 -> 다음 위치로 이동해야 하기 때문
        map[x][y][0] = 0;

        // 상어를 이동시킴
        while (true) {
            x += dx[dir];
            y += dy[dir];

            // 공간 밖으로 나가면 더 이상 갈 수 있는 공간이 없음
            if(x < 0 || x >= N || y < 0 || y >= N) break;
            // 물가고기가 없는 칸으로 이동할 수 없음
            if(map[x][y][0] == 0) continue;

            int[][][] nextMap = getNextMap(map);

            dfs(nextMap, x, y, score);
        }
    }

    private static int[][][] getNextMap(int[][][] map) {
        int[][][] nextMap = new int[N][N][2];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                nextMap[i][j][0] = map[i][j][0];
                nextMap[i][j][1] = map[i][j][1];
            }
        }
        return nextMap;
    }

    private static void move(int[][][] map) {
        Info[] info = new Info[N*N+1];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int num = map[i][j][0];

                if(num > 0) info[num] = new Info(i, j);
            }
        }

        for(int i = 1; i < info.length; i++) {
            if(info[i] == null) continue;

            int x = info[i].x;
            int y = info[i].y;
            int dir = map[x][y][1];

            for(int k = 0; k < 8; k++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 해당 방향으로 이동할 수 없음 -> 다음 방향 탐색
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny][0] == -1) {
                    dir++;
                    if(dir > 8) dir = 1;
                    continue;
                }

                // 이동
                int tempNum = map[nx][ny][0];
                int tempDir = map[nx][ny][1];

                map[nx][ny][0] = i;
                map[nx][ny][1] = dir;

                map[x][y][0] = tempNum;
                map[x][y][1] = tempDir;

                if(tempNum > 0) {
                    info[tempNum].x = x;
                    info[tempNum].y = y;
                }

                break;
            }
        }
    }

    private static class Info {
        int x;
        int y;

        public Info(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
