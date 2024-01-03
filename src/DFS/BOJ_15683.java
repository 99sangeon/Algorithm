package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15683 (감시)
public class BOJ_15683 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static List<int[]> cctvList;
    static int[] directions;
    // 북 동 남 서 (0 1 2 3)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int initialCnt;
    static int tempAns;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cctvList = new ArrayList<>();
        // 초기 사각지대 개수
        initialCnt = N * M;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] != 0) {
                    initialCnt--;
                }

                if(map[i][j] > 0 && map[i][j] < 6) cctvList.add(new int[]{i, j});
            }
        }

        directions = new int[cctvList.size()];
        dfs(0);

        System.out.println(ans);

    }

    private static void dfs(int depth) {
        if(depth == cctvList.size()) {
            visited = new boolean[N][M];
            tempAns = initialCnt;

            for(int i = 0; i < cctvList.size(); i++) {
                int[] cctv = cctvList.get(i);

                int x = cctv[0];
                int y = cctv[1];
                int type = map[x][y];
                int dir = directions[i];

                switch (type) {
                    case 1:  // 한 방향
                        monitorSection(x, y, dir);
                        break;
                    case 2:  // 두 방향 (수평)
                        monitorSection(x, y, dir);
                        monitorSection(x, y, (dir+2)%4);
                        break;
                    case 3:  // 두 방향 (수직)
                        monitorSection(x, y, dir);
                        monitorSection(x, y, (dir+1)%4);
                        break;
                    case 4:  // 세 방향
                        monitorSection(x, y, dir);
                        monitorSection(x, y, (dir+1)%4);
                        monitorSection(x, y, (dir+2)%4);
                        break;
                    case 5:  // 네 방향
                        monitorSection(x, y, dir);
                        monitorSection(x, y, (dir+1)%4);
                        monitorSection(x, y, (dir+2)%4);
                        monitorSection(x, y, (dir+3)%4);
                        break;
                }
            }

            ans = Math.min(ans, tempAns);
            return;
        }

        for(int i = 0; i < 4; i++) {
            directions[depth] = i;
            dfs(depth + 1);
        }
    }

    private static void monitorSection(int x, int y, int dir) {
        while (true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 6) break;

            if(map[nx][ny] == 0 && !visited[nx][ny]) {
                tempAns--;
                visited[nx][ny] = true;
            }

            x = nx;
            y = ny;
        }
    }
}
