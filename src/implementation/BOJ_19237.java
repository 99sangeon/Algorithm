package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19237 (어른 상어)
public class BOJ_19237 {

    static int N, M, K, ans;
    static int[][][] map;
    static Shark[] sharks;
    // 1번 인덱스 부터 상, 하, 좌, 우
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // z축 (0 -> 상어의 번호, 1 -> 상어 냄새 번호, 2 -> 냄새 남은 시간)
        map = new int[N][N][3];
        sharks = new Shark[M+1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                
                if(map[i][j][0] > 0) {
                    sharks[map[i][j][0]] = new Shark(i, j);
                }
            }
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= M; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken());
        }

        for(int num = 1; num <= M; num++) {
            sharks[num].priority = new int[5][5];
            for(int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= 4; j++) {
                    int[][] priority = sharks[num].priority;
                    priority[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        solve();
        
        System.out.println(ans);
    }

    private static void solve() {
        while (true) {
            // 모든 상어가 자신의 위치에 자신의 냄새를 뿌림
            spreadSmell();

            if(++ans > 1000) {
                ans = -1;
                return;
            }

            // 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동
            // 가능한 칸이 여러개면 우선순위를 따름
            for(int num = 1; num <= M; num++) {
                Shark shark = sharks[num];
                if(shark == null) continue;

                // 인접한 칸중 아무 냄새가 없는 칸의 방향으로 이동
                boolean flag = moveToEmpty(num, shark);

                // 그런칸이 없다면 자신의 냄새가 있는 칸의 방향으로 이동
                if(!flag) moveToOwnSmell(num, shark);
            }

            // 남은 상어가 1번뿐이라면 종료
            if(isEnd()) return;

            // 냄새는 상어가 k번 이동하고 나면 사라짐 (한번 이동 할때마다 상어냄새 남은 시간 -1)
            removeSmell();
        }
    }

    private static void spreadSmell() {
        for(int num = 1; num <= M; num++) {
            Shark shark = sharks[num];
            if(shark == null) continue;

            map[shark.x][shark.y][1] = num;
            map[shark.x][shark.y][2] = K;
        }
    }

    private static boolean moveToEmpty(int num, Shark shark) {
        int[] priority = shark.priority[shark.dir];
        boolean flag = false;

        for(int i = 1; i < priority.length; i++) {
            int dir = priority[i];
            int nx = shark.x + dx[dir];
            int ny = shark.y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny][1] > 0) continue;

            map[shark.x][shark.y][0] = 0;
            flag = true;

            // 이동하려는 칸에 상어가 있다면, 번호가 더 큰 상어는 밖으로 쫓겨남
            if(map[nx][ny][0] > 0) {
                if(map[nx][ny][0] > num) {
                    sharks[map[nx][ny][0]] = null;
                } else {
                    sharks[num] = null;
                    break;
                }
            }

            map[nx][ny][0] = num;
            shark.x = nx;
            shark.y = ny;
            shark.dir = dir;
            break;
        }

        return flag;
    }

    private static void moveToOwnSmell(int num, Shark shark) {
        int[] priority = shark.priority[shark.dir];

        for(int i = 1; i < priority.length; i++) {
            int dir = priority[i];
            int nx = shark.x + dx[dir];
            int ny = shark.y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny][1] != num) continue;

            map[shark.x][shark.y][0] = 0;
            map[nx][ny][0] = num;
            shark.x = nx;
            shark.y = ny;
            shark.dir = dir;
            break;
        }
    }

    private static boolean isEnd() {
        for (int num = 2; num <= M; num++) {
            if(sharks[num] != null) return false;
        }
        return true;
    }

    private static void removeSmell() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j][2] > 0) {
                    map[i][j][2]--;
                    if(map[i][j][2] == 0) {
                        map[i][j][1] = 0;
                    }
                }
            }
        }
    }

    private static class Shark {
        int x;
        int y;
        int dir;
        int[][] priority;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
