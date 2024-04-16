package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1759 (암호 만들기)
public class BOJ_1759 {
    static int L, C;
    static char[] arr, tmp;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        tmp = new char[L];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(sb);
    }

    private static void dfs(int start, int depth) {
        if(depth == L) {
            String str = "";
            int cnt1 = 0;  // 모음 개수
            int cnt2 = 0;  // 자음 개수

            for(int i = 0; i < L; i++) {
                str += tmp[i];

                if(tmp[i] == 'a' || tmp[i] == 'e' || tmp[i] == 'i' || tmp[i] == 'o' || tmp[i] == 'u') {
                    cnt1++;
                } else {
                    cnt2++;
                }
            }

            if(cnt1 >= 1 && cnt2 >= 2) {
                sb.append(str).append("\n");
            }

            return;
        }

        for(int i = start; i < C; i++) {
            tmp[depth] = arr[i];
            dfs(i + 1, depth+1);
        }
    }
}
