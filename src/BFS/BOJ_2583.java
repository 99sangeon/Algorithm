package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2583 (영역 구하기)
public class BOJ_2583 {
    static int M, N, K;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[M][N];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            fill(x1, y1, x2, y2);
        }

        List<Integer> ansList = solution();
        ansList.sort((o1, o2) -> Integer.compare(o1, o2));

        StringBuilder sb = new StringBuilder();

        for(int ans : ansList) {
            sb.append(ans).append(" ");
        }

        System.out.println(ansList.size());
        System.out.println(sb);
    }

    private static List<Integer> solution() {
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1) continue;

                int size = bfs(i, j);
                list.add(size);
            }
        }

        return list;
    }

    private static int bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        map[x][y] = 1;
        int size = 1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                if(nx < 0 || nx >= M || ny < 0 || ny >= N || map[nx][ny] == 1) continue;

                q.add(new int[]{nx, ny});
                map[nx][ny] = 1;
                size++;
            }
        }

        return size;
    }

    private static void fill(int x1, int y1, int x2, int y2) {
        for(int i = y1; i < y2; i++) {
            for(int j = x1; j < x2; j++) {
                map[i][j] = 1;
            }
        }
    }
}
