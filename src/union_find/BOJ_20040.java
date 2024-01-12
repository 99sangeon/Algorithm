package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20040 (사이클 게임)
public class BOJ_20040 {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N];

        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int ans = 0;

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 두 점의 부모가 같으면 사이클 형성
            if(find(a) == find(b)) {
                ans = i + 1;
                break;
            }

            union(a, b);
        }

        System.out.println(ans);
    }

    private static int find(int a) {
        if(parents[a] == a) return a;
        return find(parents[a]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a < b) {
            parents[b] = a;
        } else if (a > b) {
            parents[a] = b;
        }
    }
}
