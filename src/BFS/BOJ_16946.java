package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16946 (벽 부수고 이동하기 4)
public class BOJ_16946 {

    static int N, M;
    static int[][] arr;
    static Map<Integer, Integer> map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        map = new HashMap<>();
        sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        solution();

        System.out.println(sb);
    }

    private static void solution() {
        int group = 2;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 0) {
                    // 특정 그룹의 그룹 번호를 정해주고, 그 그룹에 몇개의 칸이 있는지 해시맵에 저장함
                    int cnt = bfs(i, j, group);
                    map.put(group++, cnt);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == 1) {  // 해당 위치에 벽이 있다면 벽을 부수고, 그 위치에서 이동할 수 있는 칸의 개수를 세어봄
                    sb.append(breakWall(i, j));
                } else {
                    sb.append(0);
                }
            }

            sb.append("\n");
        }
    }

    private static int bfs(int x, int y, int group) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        arr[x][y] = group;

        int cnt = 0;

        while(!q.isEmpty()) {
            int[] poll = q.poll();
            int cx = poll[0];
            int cy = poll[1];

            cnt++;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] != 0) continue;

                q.add(new int[]{nx, ny});
                arr[nx][ny] = group;
            }
        }

        return cnt;
    }

    private static int breakWall(int x, int y) {
        // 해당 벽을 부쉈기 때문에 이동 가능한 칸의 최소 개수는 1
        int cnt = 1;
        // 상하좌우에서 같은 그룹이 중복될 수도 있기때문에 set 자료구조 사용
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || arr[nx][ny] == 1) continue;

            // 해당 칸에서 이동할 수 있는 그룹 저장
            set.add(arr[nx][ny]);

        }

        for (int group : set) {
            // 해당 그룹에 몇개의 칸이 있는지 해시맵에서 뽑고 더해줌
            cnt += map.get(group);
        }

        return cnt%10;
    }
}
