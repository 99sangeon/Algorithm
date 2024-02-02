package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17471 (게리맨더링)
public class BOJ_17471 {

    static int N, sum1, sum2, ans = Integer.MAX_VALUE;
    static int[] population;
    static boolean[] visited;
    static List<Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N+1];
        visited = new boolean[N+1];
        map = new List[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
            map[i] = new ArrayList<>();

            sum1 += population[i];
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            while (num-- > 0) {
                map[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        comb(1);

        if(ans == Integer.MAX_VALUE) ans = -1;

        System.out.println(ans);
    }

    private static void comb(int start) {

        for(int i = start; i < N; i++) {
            visited[i] = true;
            sum1 -= population[i];
            sum2 += population[i];

            if(isAbleDivide()) {
                ans = Math.min(ans, Math.abs(sum1 - sum2));
            }

            comb(i+1);

            visited[i] = false;
            sum1 += population[i];
            sum2 -= population[i];
        }
    }


    private static boolean isAbleDivide() {
        List<Integer> group1 = new ArrayList<>();
        List<Integer> group2 = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if(visited[i]) group1.add(i);
            else  group2.add(i);
        }

        if (isAbleVisit(group1) && isAbleVisit(group2)) {
            return true;
        }

        return false;
    }

    private static boolean isAbleVisit(List<Integer> group) {
        Set<Integer> set = new HashSet<>(group);

        Queue<Integer> q = new LinkedList<>();
        q.add(group.get(0));
        set.remove(group.get(0));

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int next : map[node]) {
                if(!set.contains(next)) continue;

                q.add(next);
                set.remove(next);
            }
        }

        if(set.size() == 0) return true;

        return false;
    }
}
