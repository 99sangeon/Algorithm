package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12100 (2048 (Easy))
public class BOJ_12100 {
    static final int CNT = 5;
    static int N, ans;
    static int[][] arr;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 완전 탐색
        dfs(arr, 0);

        System.out.println(ans);
    }

    private static void dfs(int[][] map, int depth) {
        ans = Math.max(ans, getMax(map));

        if(depth == CNT) {
            return;
        }

        for(int i = 0; i < 4; i++) {
            int[][] copy = getCopyMap(map);
            
            moveMap(copy, i);
            
            dfs(copy, depth+1);
        }
    }

    private static int getMax(int[][] map) {
        int max = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }

        return max;
    }

    private static int[][] getCopyMap(int[][] map) {
        int[][] copy = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                copy[i][j] = map[i][j];
            }
        }
        
        return copy;
    }

    private static void moveMap(int[][] map, int dir) {
        Set<String> combineSet = new HashSet<>();

        if(dir == 0) { // 위
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 0) continue;
                    moveBlock(i, j, dir, map, combineSet);
                }
            }
        } else if(dir == 1) { // 아래
            for(int i = N-1; i >= 0; i--) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == 0) continue;
                    moveBlock(i, j, dir, map, combineSet);
                }
            }
        } else if(dir == 2) { // 좌
            for(int j = 0; j < N; j++) {
                for(int i = 0; i < N; i++) {
                    if(map[i][j] == 0) continue;
                    moveBlock(i, j, dir, map, combineSet);
                }
            }
        } else {   // 우
            for(int j = N-1; j >= 0; j--) {
                for(int i = 0; i < N; i++) {
                    if(map[i][j] == 0) continue;
                    moveBlock(i, j, dir, map, combineSet);
                }
            }
        }
    }

    private static void moveBlock(int x, int y, int dir, int[][] map, Set<String> combineSet) {
        int num = map[x][y];

        while(true) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= N) break;

            // 충돌했을 때 다음칸의 숫자와 같다면 합쳐짐
            // 단, 한번 합쳐진 블록일 경우 합쳐질 수 없음
            if(map[nx][ny] > 0) {
                if(map[nx][ny] == num && !combineSet.contains(nx + "," + ny)) {
                    map[x][y] = 0;
                    map[nx][ny] *= 2;
                    combineSet.add(nx + "," + ny);
                }
                break;
            }

            // 다음 블록이 비워져 있으면
            if(map[nx][ny] == 0) {
                map[x][y] = 0;
                map[nx][ny] = num;
                x = nx;
                y = ny;
            }
        }
    }
}
