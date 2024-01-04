package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/16234 (인구 이동)
public class BOJ_16234 {

    static int N, L, R, ans;
    static int[][] map;
    static boolean[][] visited;
    static List<Node> unionList;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 연합 + 인구이동 시작
        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j] == true) continue;

                    unionList = new ArrayList<>();
                    int moveCnt = movePopulation(i, j);

                    // 연합할 수 있는 국가가 없음
                    if(unionList.size() == 1) continue;

                    flag = true;
                    // 연합 국가들의 인구수 초기화
                    for(Node node : unionList) {
                        map[node.x][node.y] = moveCnt;
                    }
                }
            }

            // 더 이상 연합이 일어나지 않음
            if(!flag) break;

            ans++;
        }

        System.out.println(ans);
    }

    private static int movePopulation(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[x][y] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            Node curr = q.poll();
            unionList.add(curr);
            cnt += map[curr.x][curr.y];

            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N || visited[nx][ny]) continue;

                int diff = Math.abs(map[curr.x][curr.y] - map[nx][ny]);
                if(diff >= L && diff <= R) {
                    q.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
        // (연합한 국가들의 총 인구수 / 연합한 국가 수) 반환
        return cnt / unionList.size();
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
