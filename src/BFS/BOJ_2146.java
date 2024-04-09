package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2146 (다리 만들기)
public class BOJ_2146 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        // 각 섬에 섹션 번호를 부여해줌
        setSectionNum();

        // 각섬의 모든 지점에서 다른 섬으로 도달할 수 있는 최단 거리를 찾아 리턴함
        return findMinDistance();
    }

    private static void setSectionNum() {
        int sectionNum = 1;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    bfs1(i, j, sectionNum);
                    sectionNum++;
                }
            }
        }
    }

    private static void bfs1(int sx, int sy, int sectionNum) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0];
            int cy = curr[1];
            // 해당 위치에 섹션번호 지정
            map[cx][cy] = sectionNum;

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 0) continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    private static int findMinDistance() {
        int minDistance = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] != 0) {
                    visited = new boolean[N][N];
                    minDistance = Math.min(minDistance, bfs2(i, j, map[i][j], minDistance));
                }
            }
        }

        return minDistance;
    }

    private static int bfs2(int sx, int sy, int sectionNum, int minDistance) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sx, sy, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            // 현재까지의 최단가리보다 크거나 같은 다리길이를 지을 시 더이상 탐색할 필요 없음
            if(curr.cost >= minDistance) break;

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == sectionNum) continue;

                // 다른 섬 도달 시 다리 길이를 반환
                if(map[nx][ny] > 0) {
                    return curr.cost;
                }

                q.add(new Node(nx, ny, curr.cost + 1));
                visited[nx][ny] = true;
            }
        }

        // 해당 시작점에 다리를 놓을 수 없음
        return Integer.MAX_VALUE;
    }

    private static class Node {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }
}
