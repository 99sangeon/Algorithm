package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2623 (음악프로그램)
public class BOJ_2623 {
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
            int num = Integer.parseInt(st.nextToken()) - 1;
            int parent = Integer.parseInt(st.nextToken());

            for(int j = 0; j < num; j++) {
                int child = Integer.parseInt(st.nextToken());

                graph[parent].add(child);
                edgeCnt[child]++;

                parent = child;
            }
        }

        if(topologicalSort()) {
            System.out.println(sb);
        } else {
            System.out.println(0);
        }
    }

    private static boolean topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= N; i++) {
            if(edgeCnt[i] == 0) {
                q.add(i);
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();
            set.add(curr);
            sb.append(curr).append("\n");

            for(int next : graph[curr]) {
                edgeCnt[next]--;

                if(edgeCnt[next] == 0) {
                    q.add(next);
                }
            }
        }

        // 단방향 그래프가 아니거나, PD들이 정한 순서들 간의 모순(사이클)이 있을 경우 set의 원소 개수는 N보다 작음
        if(set.size() < N) return false;

        return true;
    }
}
