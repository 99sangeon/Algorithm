package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3190 (뱀)
public class BOJ_3190 {

    static int N, K, L, dir, ans;
    static int[][] map;
    static Deque<int[]> q;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 사과
            map[x-1][y-1] = -1;
        }

        L = Integer.parseInt(br.readLine());
        dir = 0;
        q = new LinkedList<>();
        q.addFirst(new int[]{0, 0});
        map[0][0] = 1;

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char operator = st.nextToken().charAt(0);

            if(!moveSnake(time, operator)) {
                System.out.println(ans);
                return;
            }
        }

        moveSnake(100, 'D');
        System.out.println(ans);
    }

    private static boolean moveSnake(int time, char operator) {
        for (int i = ans; i < time; i++) {

            ans++;

            int nx = q.peekFirst()[0] + dx[dir];
            int ny = q.peekFirst()[1] + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 1) {
                return false;
            }

            // 사과를 먹지 못 한다면 꼬리위치를 빈칸으로 초기화
            if(map[nx][ny] != -1) {
                int[] tail = q.pollLast();
                map[tail[0]][tail[1]] = 0;
            }

            q.addFirst(new int[]{nx, ny});
            map[nx][ny] = 1;

        }

        if(operator == 'L') { // 왼쪽 방향 전환
            dir--;
            if(dir < 0) dir = 3;
        } else {  // 오른쪽 방향 전환
            dir++;
            if(dir > 3) dir = 0;
        }

        return true;
    }
}

