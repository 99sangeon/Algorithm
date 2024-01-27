package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20056 (마법사 상어와 파이어볼)
public class BOJ_20056 {

    static int N, M, K, ans;
    static List<FireBall>[][] map;
    static int[] dx = {-1, -1, 0, 1, 1, 1 ,0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] dir = {{0, 2, 4, 6}, {1, 3, 5, 7}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new List[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new FireBall(r, c, m, s, d));
        }

        solve();

        System.out.println(ans);
    }

    private static void solve() {
        while (K-- > 0) {
            moveFireBalls();
            combineFireBalls();
        }
        addFireBallMass();
    }

    private static void moveFireBalls() {
        List<FireBall> movdedList = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j].size() == 0) continue;

                for(FireBall fireBall : map[i][j]) {
                    int r = i;
                    int c = j;

                    for(int s = 0; s < fireBall.s; s++) {
                        r += dx[fireBall.d];
                        c += dy[fireBall.d];

                        if(r < 1) r = N;
                        if(c < 1) c = N;
                        if(r > N) r = 1;
                        if(c > N) c = 1;
                    }

                    movdedList.add(new FireBall(r, c, fireBall.m, fireBall.s, fireBall.d));
                }

                // 모두 이동시킨 후 해당 위치의 파이어볼 리스트 초기화
                map[i][j].clear();
            }
        }

        // 이동한 파이어볼들을 맵에 배치
        for(FireBall fireBall : movdedList) {
            map[fireBall.r][fireBall.c].add(fireBall);
        }
    }

    private static void combineFireBalls() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int fireBallCnt = map[i][j].size();

                // 해당 위치에 파이어볼이 두개 미만이라면 합치지 않음
                if(fireBallCnt < 2) continue;

                int m = 0;
                int s = 0;
                int evenNumCnt = 0;

                for(FireBall fireBalls : map[i][j]) {
                    m += fireBalls.m;
                    s += fireBalls.s;

                    if(fireBalls.d % 2 == 0) evenNumCnt++;
                }

                // 모두 합친 후 해당 위치의 파이어볼 리스트 초기화
                map[i][j].clear();

                m /= 5;
                s /= fireBallCnt;

                if(m == 0) continue;

                // 4개의 파이어볼로 나뉨
                for(int k = 0; k < 4; k++) {
                    if(evenNumCnt == 0 || evenNumCnt == fireBallCnt) {
                        map[i][j].add(new FireBall(i, j, m, s, dir[0][k]));
                    } else {
                        map[i][j].add(new FireBall(i, j, m, s, dir[1][k]));
                    }
                }
            }
        }
    }

    private static void addFireBallMass() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if (map[i][j].size() == 0) continue;

                for(FireBall fireBalls : map[i][j]) {
                    ans += fireBalls.m;
                }
            }
        }
    }

    private static class FireBall {
        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
