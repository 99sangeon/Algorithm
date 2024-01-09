package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2239 (스도쿠)
public class BOJ_2239 {

    static int N = 9;
    static boolean flag = false;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }

        dfs(0, 0);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int row, int col) {

        if(col == N) {
            row++;
            col = 0;
        }

        if(row == N) {
            flag = true;
            return;
        }

        if(map[row][col] != 0) {
            dfs(row, col+1);
            return;
        }

        for(int num = 1; num < 10; num++) {
            if(isPossible(row, col, num)) {
                map[row][col] = num;
                dfs(row, col+1);
                if(flag == true) break;
                map[row][col] = 0;
            }
        }
    }

    private static boolean isPossible(int row, int col, int num) {

        boolean[] visited = new boolean[N+1];

        for(int i = 0; i < N; i++) {
            // 행에서 숫자 체크
            visited[map[row][i]] = true;
            // 열에서 숫자 체크
            visited[map[i][col]] = true;
        }

        row = 3 * (row/3);
        col = 3 * (col/3);

        // 정사각형에서 숫자 체크
        for(int i = row; i < row+3; i++) {
            for(int j = col; j < col+3; j++) {
                visited[map[i][j]] = true;
            }
        }

        if(visited[num] == true) return false;

        return true;
    }
}
