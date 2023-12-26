package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12851 (숨바꼭질 2)
public class BOJ_12851 {

    static int N, K;
    static int[] distances;
    static int[] dx = {-1, 1, 2}; // 후진, 전진, 순간이동
    static int ans;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(N >= K) {   // 목표 지점보다 더 앞에 있는 경우 후진을 통해서만 이동 가능
            System.out.println(N - K);
            System.out.println(1);
            return;
        }

        distances = new int[100001];
        Arrays.fill(distances, Integer.MAX_VALUE);

        bfs();

        System.out.println(ans);
        System.out.println(cnt);

    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 0));

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if(cnt == 0 && curr.loc == K) { // 목표 지점 첫 도착 -> 최단거리
                ans = curr.dis;
                cnt++;
            } else if (cnt > 0) {
                if(curr.dis > ans) return;
                if(curr.loc == K) cnt++;  // 다른 경로를 통해 최단거리로 목표 지점 도착
            }

            int nDis = curr.dis + 1;

            for(int i = 0; i < 3; i++) {
                int nLoc = curr.loc;
                if(i == 2) {
                    nLoc *= dx[i];
                } else {
                    nLoc += dx[i];
                }

                if(nLoc < 0 || nLoc > 100000) continue;

                if(nDis <= distances[nLoc]) {
                    q.add(new Node(nLoc, nDis));
                    distances[nLoc] = nDis;
                }
            }
        }
    }

    public static class Node {
        int loc;
        int dis;

        public Node(int loc, int dis) {
            this.loc = loc;
            this.dis = dis;
        }
    }
}
