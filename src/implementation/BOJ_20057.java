package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20057 (마법사 상어와 토네이도)
public class BOJ_20057 {

    static int N, ans, x, y, dir, moveLength;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 토네이도가 이동하는 방향
    static int[] dy = {-1, 0, 1, 0}; // 토네이도가 이동하는 방향
    static int[][] sx = {
            {-1, 1, -2, 2, 0, -1, 1, -1, 1},
            {-1, -1, 0, 0, 2, 0, 0, 1, 1},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1},
            {1, 1, 0, 0, -2, 0, 0, -1, -1}
    }; // 모래가 퍼지는 방향
    static int[][] sy = {
            {1, 1, 0, 0, -2, 0, 0, -1, -1},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1},
            {-1, -1, 0, 0, 2, 0, 0, 1, 1},
            {-1, 1, -2, 2, 0, -1, 1, -1, 1}
    }; // 모래가 퍼지는 방향
    static int[] percents = {1, 1, 2, 2, 5, 7, 7, 10, 10};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(ans);
    }

    private static void solve() {
        x = N/2;
        y = N/2;
        dir = 0;
        moveLength = 1;
        int moveCnt = 0;

        while (true) {
            if(x == 0 && y == 0) break;

            // 토네이도가 한쪽방향으로 이동
            move();
            // 방향 전환
            dir++;
            // move()함수 두번 호출 시 다음 move()함수 호출 시 이동 거리 +1
            if(++moveCnt % 2 == 0) moveLength++;
        }
    }

    private static void move() {
        for(int i = 0; i < moveLength; i++) {
            if(x == 0 && y == 0) break;

            // 토네이도가 한칸 이동
            x += dx[dir%4];
            y += dy[dir%4];

            int remainedAmount = map[x][y];

            // 모래가 퍼짐
            for(int p = 0; p <= percents.length; p++) {
                int nx = x;
                int ny = y;
                int spreadAmount;

                if(p < percents.length) {
                    nx += sx[dir%4][p];
                    ny += sy[dir%4][p];
                    spreadAmount = map[x][y] * percents[p] / 100;
                } else {
                    nx += dx[dir%4];
                    ny += dy[dir%4];
                    spreadAmount = remainedAmount;
                }

                remainedAmount -= spreadAmount;

                // 맵 밖으로 모래가 나간다면 밖으로 나간 모래합 누적
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    ans += spreadAmount;
                    continue;
                }

                // 맵 밖으로 모래가 나가지 않는다면 해당위치에 모래합 누적
                map[nx][ny] += spreadAmount;
            }
        }
    }
}
