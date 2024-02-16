package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2252 (줄 세우기)
public class BOJ_2252 {

    static int N, M;
    static List<Integer>[] graph;
    static int[] edgeCnt;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N+1];
        edgeCnt = new int[N+1];
        sb = new StringBuilder();

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            edgeCnt[B]++;
        }

        // 위상 정렬 알고리즘을 이용해 그래프 상에서의 정렬이 가능함
        topologicalSort();

        System.out.println(sb);
    }

    private static void topologicalSort() {
        Queue<Integer> q  = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(edgeCnt[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append(" ");

            for(int next : graph[curr]) {
                // 진입하는 간선이 없으면 큐에 추가함
                if(--edgeCnt[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}
