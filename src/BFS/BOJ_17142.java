package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17142 (연구소 3)
public class BOJ_17142 {

    static int N, M;
    static int[][] map;
    static List<Node> viruses;
    static Node[] selected;
    static int zero;
    static int ans = Integer.MAX_VALUE;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        viruses = new ArrayList<>();
        selected = new Node[M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) {
                    zero++;
                    continue;
                }

                if(map[i][j] == 2) {
                    viruses.add(new Node(i, j, 0));
                }
            }
        }

        // M개의 바이러스 조합을 찾고, 활성화시켜 바이러스 전이
        comb(0, 0);

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);

    }

    private static void comb(int cnt, int start) {
        if(cnt == M) {
            // 활성화 바이러스 전이 시간 탐색
            int sec = bfs();
            // 모든 빈 공간 전이 가능
            if(sec != -1) ans = Math.min(ans, sec);
            return;
        }

        for(int i = start; i < viruses.size(); i++) {
            selected[cnt] = viruses.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    private static int bfs() {

        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for(Node node : selected) {
            q.add(node);
            visited[node.x][node.y] = true;
        }

        int spreadCnt = 0;
        int tempAns = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny] || map[nx][ny] == 1) continue;

                q.add(new Node(nx, ny, curr.dis + 1));
                visited[nx][ny] = true;

                // 비활성 바이러스가 아닌 빈 공간으로 전이할 때 전이 숫자 증가 및 전이 시간 초기화
                if(map[nx][ny] == 0) {
                    spreadCnt++;
                    tempAns = Math.max(tempAns, curr.dis + 1);
                }
            }
        }

        if(spreadCnt != zero) return -1;
        return tempAns;
    }

    private static class Node {
        int x;
        int y;
        int dis;

        public Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
