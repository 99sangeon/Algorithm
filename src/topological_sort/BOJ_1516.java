package topological_sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1516 (게임 개발)
public class BOJ_1516 {
    static int N;
    static List<Integer>[] graph;
    static int[] timeArr, edgeCnt, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new List[N+1];
        timeArr = new int[N+1];
        edgeCnt = new int[N+1];
        answer = new int[N+1];

        for(int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            timeArr[i] = Integer.parseInt(st.nextToken());

            while(true) {
                int token = Integer.parseInt(st.nextToken());

                if(token == -1) break;

                graph[token].add(i);
                edgeCnt[i]++;
            }
        }

        // 위상 정렬을 통해 그래프상에서 정렬 가능
        System.out.println(solution());
    }

    private static StringBuilder solution() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= N; i++) {
            if(edgeCnt[i] == 0) {
                q.add(i);
                answer[i] = timeArr[i];
            }
        }

        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int next : graph[curr]) {
                answer[next] = Math.max(answer[next], answer[curr]);

                if(--edgeCnt[next] == 0) {
                    q.add(next);
                    answer[next] += timeArr[next];
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(answer[i]).append("\n");
        }

        return sb;
    }
}
