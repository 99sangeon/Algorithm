package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5427 (불)
public class BOJ_5427 {
    static int T, W, H;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> fireQ, sangQ;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            visited = new boolean[H][W];
            fireQ = new LinkedList<>();
            sangQ = new LinkedList<>();

            for(int i = 0; i < H; i++) {
                String line = br.readLine();
                for(int j = 0; j < W; j++) {
                    map[i][j] = line.charAt(j);

                    if(map[i][j] == '*') {
                        fireQ.add(new int[]{i, j});
                    } else if (map[i][j] == '@') {
                        sangQ.add(new int[]{i, j});
                        map[i][j] = '.';
                        visited[i][j] = true;
                    }
                }
            }

            solution();
        }

        System.out.println(sb);
    }

    private static void solution() {
        int sec = 0;
        boolean flag = false;

        while (!sangQ.isEmpty()) {
            sec++;

            // 불이 번짐
            spreadFire();

            // 상근이가 이동함 -> 탈출 성공 시 flag = true
            if(moveSang()) {
                flag = true;
                break;
            }
        }

        if(flag) {
            sb.append(sec);
        } else {
            sb.append("IMPOSSIBLE");
        }

        sb.append("\n");
    }

    private static void spreadFire() {
        int size = fireQ.size();

        while(size-- > 0) {
            int[] fire = fireQ.poll();

            for(int i = 0; i < 4; i++) {
                int nx = fire[0] + dx[i];
                int ny = fire[1] + dy[i];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W || map[nx][ny] != '.') continue;

                fireQ.add(new int[]{nx, ny});
                map[nx][ny] = '*';
            }
        }
    }

    private static boolean moveSang() {
        int size = sangQ.size();

        while(size-- > 0) {
            int[] sang = sangQ.poll();

            for(int i = 0; i < 4; i++) {
                int nx = sang[0] + dx[i];
                int ny = sang[1] + dy[i];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W) return true;
                if(map[nx][ny] != '.' || visited[nx][ny]) continue;

                sangQ.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        return false;
    }
}
