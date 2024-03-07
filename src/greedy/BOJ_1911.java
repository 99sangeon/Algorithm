package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1911 (흙길 보수하기)
public class BOJ_1911 {
    static int N, L, ans;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[i][0] = start;
            arr[i][1] = end;
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        Arrays.sort(arr, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        // 이전 웅덩이를 덮을때 사용한 마지막 나무 판자의 끝점
        int nextStart = 0;

        for(int i = 0; i < N; i++) {
            int start = arr[i][0];
            int end = arr[i][1];

            if(nextStart > end) continue;

            // 이전 웅덩이를 덮을때 나무판자가 현재 웅덩이의 영역을 침범 -> 현재 웅덩이의 시작점 = nextStart
            if(nextStart > start) {
                start = nextStart;
            }

            // 웅덩이의 길이
            int length = end - start;

            if(length % L == 0) {  // 웅덩이의 길이를 딱 맞게 나무 판자로 덮음
                ans += length / L;
                nextStart = end;
            } else {   // 웅덩이의 길이를 초과해서 나무 판자를 덮음
                ans += length / L + 1;
                nextStart = end + (L - length % L);
            }
        }
    }
}
