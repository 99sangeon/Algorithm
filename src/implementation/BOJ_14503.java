package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14503 (로봇 청소기)
public class BOJ_14503 {

    static int N, M, cx, cy, dir;
    static int[][] map;
    // 북, 동, 남, 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 로봇 청소기 위치
        st = new StringTokenizer(br.readLine());
        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        // d가 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽을 바라보고 있는 것
        dir = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = startCleaning();

        System.out.println(ans);
    }

    private static int startCleaning() {

        int cleaningCnt = 0;

        while (true) {

            if(map[cx][cy] == 0) {
                map[cx][cy] = 2;
                cleaningCnt++;
            }

            boolean isCleaning = false;

            for(int i = 0; i < 4; i++) {

                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
                    isCleaning = true;
                    break;
                }
            }

            if(isCleaning) {
                dir--;
                if(dir < 0) dir = 3;

                int nx = cx;
                int ny = cy;

                switch (dir) {
                    case 0:
                        nx--;
                        break;
                    case 1:
                        ny++;
                        break;
                    case 2:
                        nx++;
                        break;
                    case 3:
                        ny--;
                        break;
                }

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != 0) continue;

                cx = nx;
                cy = ny;
                continue;
            }

            if(!isCleaning) {
                int nx = cx;
                int ny = cy;

                switch (dir) {
                    case 0:
                        nx++;
                        break;
                    case 1:
                        ny--;
                        break;
                    case 2:
                        nx--;
                        break;
                    case 3:
                        ny++;
                        break;
                }

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 1) break;

                cx = nx;
                cy = ny;
            }
        }

        return cleaningCnt;
    }
}
