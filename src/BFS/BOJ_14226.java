package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/14226 (이모티콘)
public class BOJ_14226 {
    static int S;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = Integer.parseInt(br.readLine());
        visited = new boolean[S+1][S+1];

        int ans = bfs();

        System.out.println(ans);
    }

    private static int bfs() {
        int ans = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 1, 0));

        while(!q.isEmpty()) {
            Node curr = q.poll();

            if(curr.display == S) {
                ans = curr.sec;
                break;
            }

            // 해당 이모티콘 개수를 더 빠르게 만들 수 있는 경우를 이미 찾음
            // 더 이상 탐색하지 않음 -> 백트래킹

            // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장한다.
            if(curr.display > 0 && !visited[curr.display][curr.display]) {
                q.add(new Node(curr.sec + 1, curr.display, curr.display));
                visited[curr.display][curr.display] = true;
            }
            // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 한다.
            if(curr.clipBoard > 0 && curr.display + curr.clipBoard <= S && !visited[curr.display + curr.clipBoard][curr.clipBoard]){
                q.add(new Node(curr.sec + 1, curr.display + curr.clipBoard, curr.clipBoard));
                visited[curr.display + curr.clipBoard][curr.clipBoard] = true;
            }
            // 3. 화면에 있는 이모티콘 중 하나를 삭제한다.
            if(curr.display > 0 && !visited[curr.display - 1][curr.clipBoard]) {
                q.add(new Node(curr.sec + 1, curr.display - 1, curr.clipBoard));
                visited[curr.display - 1][curr.clipBoard] = true;
            }
        }

        return ans;
    }

    private static class Node {
        int sec;
        int display;
        int clipBoard;

        public Node(int sec, int display, int clipBoard) {
            this.sec = sec;
            this.display = display;
            this.clipBoard = clipBoard;
        }
    }
}
