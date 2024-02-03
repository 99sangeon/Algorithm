package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4179 (불!)
public class BOJ_4179 {

    static int R, C, ans;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> fireQ, jihoonQ;
    static int[] dx = {-1, 1, 0 ,0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        fireQ = new LinkedList<>();
        jihoonQ = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            String row = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = row.charAt(j);

                if(map[i][j] == 'J') jihoonQ.add(new Node(i, j, 0));
                if(map[i][j] == 'F') fireQ.add(new Node(i, j, 0));
            }
        }

        solution();

        if(ans == 0) {
            System.out.println("IMPOSSIBLE");
            return;
        }

        System.out.println(ans);
    }

    private static void solution() {
        int currTime = 0;

        // 지훈이가 이동할 공간이 남아 있고 아직 탈출하지 못했을 때 이동은 계속 됨
        while (!jihoonQ.isEmpty() && ans == 0) {
            moveFire(currTime);
            moveJihoon(currTime);
            currTime++;
        }
    }

    private static void moveFire(int currTime) {
        while (!fireQ.isEmpty() && fireQ.peek().time == currTime) {
            Node currFire = fireQ.poll();

            for (int i = 0; i < 4; i++) {
                int nx = currFire.x + dx[i];
                int ny = currFire.y + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == 'F' || map[nx][ny] == '#') continue;

                map[nx][ny] = 'F';
                fireQ.add(new Node(nx, ny, currTime + 1));
            }
        }
    }

    private static void moveJihoon(int currTime) {
        while (!jihoonQ.isEmpty() && jihoonQ.peek().time == currTime) {
            Node currJihoon = jihoonQ.poll();

            // 탈출 성공
            if(currJihoon.x == 0 || currJihoon.x == R-1 || currJihoon.y == 0 || currJihoon.y == C-1) {
                ans = currTime+1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = currJihoon.x + dx[i];
                int ny = currJihoon.y + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == 'F' || map[nx][ny] == '#' || visited[nx][ny]) continue;

                visited[nx][ny] = true;
                jihoonQ.add(new Node(nx, ny, currTime + 1));
            }
        }
    }

    private static class Node {
        int x;
        int y;
        int time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
