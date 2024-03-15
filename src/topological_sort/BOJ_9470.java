package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9470 (Strahler 순서)
public class BOJ_9470 {
    static int T, K, M, P;
    static List<Integer>[] lists;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            lists = new List[M];
            nodes = new Node[M];

            for(int j = 0; j < M; j++) {
                lists[j] = new ArrayList<>();
                nodes[j] = new Node();
            }

            for(int j = 0; j < P; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) - 1;
                lists[from].add(to);
                nodes[to].edgeCnt++;
            }

            // 위상 정렬을 이용해 가장 마지막 노드의 Strahler가 정답
            int ans = solution();

            sb.append(K).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static int solution() {
        Queue<Integer> q = new LinkedList<>();
        int ans = 0;

        // 강의 근원인 노드의 순서는 1이다.
        for(int i = 0; i < M; i++) {
            if(nodes[i].edgeCnt == 0) {
                nodes[i].strahler = 1;
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int from = q.poll();
            Node curr = nodes[from];

            ans = curr.strahler;

            for(int to : lists[from]) {
                Node next = nodes[to];

                // 나머지 노드는 그 노드로 들어오는 강의 순서 중 가장 큰 값을 i라고 했을 때, 들어오는 모든 강 중에서 Strahler 순서가 i인 강이 1개이면 순서는 i
                if(curr.strahler == next.strahler) {
                    next.strahlerCnt++;
                } else if(curr.strahler > next.strahler) {
                    next.strahler = curr.strahler;
                    next.strahlerCnt = 1;
                }

                if(--next.edgeCnt == 0) {
                    // 2개 이상이면 순서는 i+1이다.
                    if(next.strahlerCnt > 1) {
                        next.strahler++;
                    }
                    q.add(to);
                }
            }
        }

        return ans;
    }

    private static class Node {
        int edgeCnt;
        int strahler;
        int strahlerCnt;
    }
}
