package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1937 (욕심쟁이 판다)
public class BOJ_1937 {

    static int N, ans;
    static int[][] map, dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        // 모든 포인트에서 판다를 풀어봄
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(i, j));
            }
        }
    }

    private static int dfs(int x, int y) {
        // 해당 지점에서 방문할 수 있는 지점의 개수를 이미 알고 있다면 더 이상 탐색하지 않아도 됨
        if(dp[x][y] > 0) {
            return dp[x][y];
        }

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] <= map[x][y]) continue;

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny));
        }

        dp[x][y]++;

        return dp[x][y];
    }

}

