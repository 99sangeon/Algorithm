package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/1038 (감소하는 수)
public class BOJ_1038 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long ans = bfs();

        System.out.println(ans);
    }

    private static long bfs() {
        int cnt = 0;

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 10));

        while(!q.isEmpty()) {
            Node curr = q.poll();

            for(int i = 0; i < curr.lastNum; i++) {
                long nextNum = curr.num * 10 + i;
                q.add(new Node(nextNum, i));

                if(cnt == N) {
                    return nextNum;
                }

                cnt++;
            }
        }

        return -1;
    }

    private static class Node {
        long num;
        int lastNum;

        public Node(long num, int lastNum) {
            this.num = num;
            this.lastNum = lastNum;
        }
    }
}
