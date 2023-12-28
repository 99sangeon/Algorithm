package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1939 (중량제한)
public class BOJ_1939 {

    static int N, M, start, end, min, max;
    static List<Node>[] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new List[N+1];
        visited = new boolean[N+1];

        for(int i = 1; i <= N; i++) {
            map[i] = new ArrayList<>();
        }

        min = Integer.MAX_VALUE;
        max = 0;

        for(int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            min = Math.min(min, weight);
            max = Math.max(max, weight);

            map[a].add(new Node(b, weight));
            map[b].add(new Node(a, weight));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 정답은 가장 낮은 무게 ~ 가장 높은 무게 -> 가장 높은 무게가 10억이기 때문에 모든 경우의 수를 탐색할 수 없음
        // 이분탐색을 사용해 해결
        int ans = binarySearch(min, max);

        System.out.println(ans);
    }

    private static int binarySearch(int left, int right) {

        while (left <= right) {
            int mid = (left + right) / 2;

            // BFS를 이용해 해당무게(mid)를 출발점 부터 목적지까지 옮길 수 있는 지 탐색
            visited = new boolean[N+1];
            boolean isMove = bfs(mid);

            if(isMove) {  // 건널 수 있으면 중량 증가
                left = mid + 1;
            } else {      // 건널 수 없으면 중량 감소
                right = mid - 1;
            }
        }

        return right;
    }

    private static boolean bfs(int weight) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if(curr == end) return true;

            for(Node next : map[curr]) {
                if(visited[next.num] || next.weight < weight) continue;
                q.add(next.num);
                visited[next.num] = true;
            }
        }

        return false;
    }

    private static class Node {
        int num;
        int weight;

        public Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }
}
