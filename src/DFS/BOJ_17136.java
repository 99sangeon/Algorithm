package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17136 (색종이 붙이기)
public class BOJ_17136 {
    static final int N = 10;
    static int ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] papers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[N][N];
        papers = new int[6];

        Arrays.fill(papers, 5);

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++ ) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        System.out.println(ans);
    }

    private static void dfs(int x, int y, int cnt) {
        if(x > 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if(y > 9) {
            dfs(x+1, 0, cnt);
            return;
        }

        if(map[x][y] == 0) {
            dfs(x, y + 1, cnt);
        }

        for(int s = 1; s <= 5; s++) {
            if(papers[s] == 0 || !check(x, y, s)) continue;

            // 해당 칸에 해당 사이즈의 색종이를 붙임
            fill(x, y, s, 0);
            papers[s]--;
            dfs(x, y + 1, cnt+1);
            // 해당 칸에 해당 사이즈의 색종이를 땜
            fill(x, y, s, 1);
            papers[s]++;
        }
    }

    // 해당 칸에 해당 사이즈의 색종이를 붙일 수 있는지?
    private static boolean check(int x, int y, int size) {
        if(x + size > N || y + size > N) return false;

        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(map[i][j] == 0) return false;
            }
        }
        return true;
    }

    private static void fill(int x, int y, int size, int type) {
        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                map[i][j] = type;
            }
        }
    }
}
