package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15684 (사다리 조작)
public class BOJ_15684 {
    static int N, M, H, ans = Integer.MAX_VALUE;
    static boolean[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new boolean[H+1][N+1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            // b번 세로선과 b+1번 세로선을 a번 점선 위치에서 연결
            map[a][b] = true;
        }

        solution();

        if(ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        System.out.println(ans);
    }

    private static void solution() {
        comb(1, 0);
    }

    private static void comb(int start, int cnt) {
        if(simulation()) {
            ans = Math.min(ans, cnt);
        }
        
        if(start == N || cnt == 3 || ans == 0) return;

        for(int n = start; n < N; n++) {
            for(int h = 1; h <= H; h++) {
                // 해당 위치와 좌우에 사다리가 없다면 사다리를 놓을 수 있음 
                if(!map[h][n] && !map[h][n-1] && !map[h][n+1]) {
                    map[h][n] = true;
                    comb(n, cnt + 1);
                    map[h][n] = false;
                }
            }
        }
    }

    private static boolean simulation() {
        for(int n = 1; n <= N; n++) {
            int loc = n;

            for(int h = 1; h <= H; h++) {
                if(map[h][loc]) { // 오른쪽으로 이동
                    loc++;
                } else if (map[h][loc-1]) { // 왼쪽으로 이동
                    loc--;
                }
            }

            if(loc != n) {
                return false;
            }
        }

        return true;
    }
}
