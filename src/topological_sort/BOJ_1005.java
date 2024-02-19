package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1005 (ACM Craft)
public class BOJ_1005 {
    static int T, N, K, W;
    static List<Integer>[] graph;
    static int[] arr, edgeCnt, time;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N+1];
            edgeCnt = new int[N+1];
            time = new int[N+1];
            graph = new List[N+1];

            st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                graph[i] = new ArrayList<>();
            }

            for(int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                edgeCnt[to]++;
            }

            W = Integer.parseInt(br.readLine());

            // 특정 건물을 짓기위한 순서가 존재함 -> 그래프 상에서의 정렬 -> 위상정렬
            solution();

            sb.append(time[W]).append("\n");
        }

        System.out.println(sb);
    }

    private static void solution() {
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(edgeCnt[i] == 0) {
                q.add(i);
                time[i] = arr[i];
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();

            if(curr == W) {
                return;
            }

            for(int next : graph[curr]) {
                // next 건물을 짓기 위해서 curr 건물이 반드시 지어진 상태
                // next 건물을 짓기 위한 시간 - > Math.max(time[next], time[curr] + arr[next])
                time[next] = Math.max(time[next], time[curr] + arr[next]);

                // next 건물을 짓기 위해서 next 이전의 건물들이 모두 지어진 상태
                if(--edgeCnt[next] == 0) {
                    q.add(next);
                }
            }
        }
    }
}
