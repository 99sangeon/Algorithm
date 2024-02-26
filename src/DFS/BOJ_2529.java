package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2529 (부등호)
public class BOJ_2529 {
    static int N;
    static char[] arr;
    static boolean[] visited;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new char[N+1];
        visited = new boolean[10];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        dfs(0, "");

        System.out.println(list.get(list.size() - 1));
        System.out.println(list.get(0));
    }

    private static void dfs(int depth, String num) {
        if(depth == N+1) {
            list.add(num);
            return;
        }

        for(int i = 0; i <= 9; i++) {
            if(visited[i]) continue;
            if(depth > 0 && arr[depth] == '<' && num.charAt(depth - 1) - '0' > i) continue;
            if(depth > 0 && arr[depth] == '>' && num.charAt(depth - 1) - '0' < i) continue;

            visited[i] = true;
            dfs(depth+1, num+i);
            visited[i] = false;
        }
    }
}
