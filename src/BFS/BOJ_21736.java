package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21736 (헌내기는 친구가 필요해)
public class BOJ_21736 {

    static int N, M, ans;
    static char[][] map;
    static Queue<int[]> q;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        q = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                // 시작점(도연이의 처음 위치)를 큐에 삽입
                if(map[i][j] == 'I') {
                    q.add(new int[]{i, j});
                    map[i][j] = 'X';
                }
            }
        }

        bfs();

        if(ans > 0) {
            System.out.println(ans);
        } else {
            System.out.println("TT");
        }
    }

    private static void bfs() {
        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = curr[0] + dx[i];
                int ny = curr[1] + dy[i];

                // 해당 위치로 이동 불가
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] == 'X') continue;

                // 해당 위치에 사람이 있음
                if(map[nx][ny] == 'P') ans++;

                q.add(new int[]{nx, ny});
                // 중복을 막기 위해 해당 위치 방문 처리
                map[nx][ny] = 'X';
            }
        }
    }
}
