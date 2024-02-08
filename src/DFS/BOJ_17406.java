package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17406 (배열 돌리기 4)
public class BOJ_17406 {

    static int N, M, K, ans = Integer.MAX_VALUE;
    static int[][] map, rotation;
    static int[] order;
    static boolean[] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        rotation = new int[K][3];
        order = new int[K];
        visited = new boolean[K];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            rotation[i][0] = r;
            rotation[i][1] = c;
            rotation[i][2] = s;
        }

        dfs(0);

        System.out.println(ans);
    }

    private static void dfs(int depth) {
        if(depth == K) {
            int[][] copyMap = copy();

            for(int i = 0; i < K; i++) {
                rotate(copyMap, rotation[order[i]][0], rotation[order[i]][1], rotation[order[i]][2]);
            }

            ans = Math.min(ans, getMin(copyMap));
        }

        for(int i = 0; i < K; i++) {
            if(visited[i]) continue;

            order[depth] = i;
            visited[i] = true;
            dfs(depth+1);
            visited[i] = false;
        }
    }

    private static int[][] copy() {
        int[][] copyMap = new int[N+1][M+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;
    }

    private static int getMin(int[][] copyMap) {
        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= N; i++) {
            int sum = 0;

            for(int j = 1; j <= M; j++) {
                sum += copyMap[i][j];
            }

            min = Math.min(min, sum);
        }

        return min;
    }

    private static void rotate(int[][] copyMap, int r, int c, int s) {
        if(s == 0) return;

        int sx = r - s;
        int sy = c - s;
        int ex = r + s;
        int ey = c + s;

        int cx = sx;
        int cy = sy;
        int dir = 0;
        int value = copyMap[cx][cy];

        while (true) {
            if(dir >= 4) break;

            int nx = cx + dx[dir];
            int ny = cy + dy[dir];

            if(nx < sx || nx > ex || ny < sy || ny > ey) {
                dir++;
                continue;
            }

            int temp = copyMap[nx][ny];
            copyMap[nx][ny] = value;
            value = temp;

            cx = nx;
            cy = ny;
        }

        rotate(copyMap, r, c, s-1);
    }
}
