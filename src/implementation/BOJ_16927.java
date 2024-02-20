package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16927 (배열 돌리기 2)
public class BOJ_16927 {
    static int N, M, R;
    static int[][] arr;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x = 0;
        int y = 0;

        while(N > 1 && M > 1) {
            // 배열의 가장 바깥 부분부터 회전시킴
            solution(x, y, N, M);
            x++;
            y++;
            N -= 2;
            M -= 2;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solution(int x, int y, int N, int M) {
        // 모듈러 연산을 통해 최소 회전 횟수를 구함
        int cycleCnt = R % ((N * 2) + ((M - 2) * 2));

        while (cycleCnt-- > 0) {
            // 반시계 방향으로 1회 회전시킴
            rotate(x, y, N, M);
        }
    }

    private static void rotate(int x, int y, int N, int M) {
        int dir = 0;
        int temp = arr[x][y];

        int cx = x;
        int cy = y;

        while(true) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];

            if(nx < x || nx >= x+N || ny < y || ny >= y+M) {
                dir++;
                continue;
            }

            if(nx == x && ny == y && dir == 3) {
                arr[cx][cy] = temp;
                return;
            }

            arr[cx][cy] = arr[nx][ny];

            cx = nx;
            cy = ny;
        }
    }
}
