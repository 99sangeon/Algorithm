package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/3197 (백조의 호수)
public class BOJ_3197 {
    static int R, C;
    static int[] start, target;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> swanQ, waterQ;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        swanQ = new LinkedList<>();
        waterQ = new LinkedList<>();

        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'L') {
                    if(start == null) {
                        start = new int[]{i, j};
                    } else {
                        target = new int[]{i, j};
                    }
                }

                if(map[i][j] != 'X') {
                    waterQ.add(new int[]{i, j});
                }
            }
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        int day = 0;
        swanQ.add(start);
        visited[start[0]][start[1]] = true;

        while(true) {
            // 백조가 물가를 이동하다 타겟 백조를 만다면 종료
            if(bfs()) {
                return day;
            }

            // 물과 닿아있는 얼음을 녹임
            breakIce();

            day++;
        }
    }

    private static boolean bfs() {
        Queue<int[]> nextQ = new LinkedList<>();

        while(!swanQ.isEmpty()) {
            int[] poll = swanQ.poll();
            int cx = poll[0];
            int cy = poll[1];

            if(cx == target[0] && cy == target[1]) {
                return true;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) continue;

                visited[nx][ny] = true;

                // 다음 날 백조가 탐색을 시작할 위치
                if(map[nx][ny] == 'X') {
                    nextQ.add(new int[]{nx, ny});
                    continue;
                }

                swanQ.add(new int[]{nx, ny});
            }
        }

        swanQ = nextQ;

        return false;
    }

    private static void breakIce() {
        int size = waterQ.size();

        while(size-- > 0) {
            int[] poll = waterQ.poll();
            int cx = poll[0];
            int cy = poll[1];

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] != 'X') continue;

                // 얼음이 녹아 물이 됨
                map[nx][ny] = '.';
                waterQ.add(new int[]{nx, ny});
            }
        }
    }
}
