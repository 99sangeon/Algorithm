package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9663 (N-Queen)
public class BOJ_9663 {

    static int N, ans;
    static int[] dx = {-1, 1, -1, 1, 0, 0};
    static int[] dy = {-1, 1, 1, -1, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        dfs(0);

        System.out.println(ans);
    }

    private static void dfs(int depth) {
        if(depth == N) {
            ans++;
            return;
        }

        for(int i = 0; i < N; i++) {
            // 해당 위치에 배치할 수 있다면 다음 깊이 방문
            if(isAble(i, depth)) {
                visited[i][depth] = true;
                dfs(depth + 1);
                visited[i][depth] = false;
            }
        }
    }

    private static boolean isAble(int x, int y) {
        for(int i = 0; i < dx.length; i++) {
            int nx = x;
            int ny = y;

            while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if(visited[nx][ny]) return false;

                nx += dx[i];
                ny += dy[i];
            }
        }

        return true;
    }
}
