package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://www.acmicpc.net/problem/11559 (Puyo Puyo)
public class BOJ_11559 {
    static final int N = 12, M = 6;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        
        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        int ans = 0;

        while(true) {
            boolean flag = false;
            visited = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] == '.' || visited[i][j]) continue;

                    // 폭발 위치 리스트
                    List<int[]> explosionList = new ArrayList<>();

                    // 해당 지점에서 폭발이 가능하면 모든 폭발 지점을 빈 공간으로 만듬
                    if(isExplode(i, j, map[i][j], explosionList)) {
                        for(int[] loc : explosionList) {
                            map[loc[0]][loc[1]] = '.';
                            flag = true;
                        }
                    }
                }
            }

            // 폭발이 한번도 일어나지 않으면 멈춤
            if(!flag) {
                break;
            }

            // 폭발이 발생 했으면 맵을 재배치 해줌
            rearrangeMap();

            ans++;
        }

        return ans;
    }

    private static boolean isExplode(int x, int y, char color, List<int[]> explosionList) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            explosionList.add(curr);

            for(int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] || map[nx][ny] != color) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }

        if(explosionList.size() < 4) return false;

        return true;
    }

    private static void rearrangeMap() {
        // 폭발 후 블럭들을 아래에서 부터 위로 순차적으로 내림
        for(int i = N - 2; i >= 0; i--) {
            for(int j = 0; j < M; j++) {
                downBlock(i, j, map[i][j]);
            }
        }
    }

    private static void downBlock(int x, int y, char color) {
        int target = -1;

        for(int i = x+1; i < N; i++) {
            if(map[i][y] == '.') {
                target = i;
            } else {
                break;
            }
        }

        if(target > -1) {
            map[x][y] = '.';
            map[target][y] = color;
        }
    }
}
