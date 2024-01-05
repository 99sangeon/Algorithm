package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20055 (컨베이어 벨트 위의 로봇)
public class BOJ_20055 {

    static int N, K, start, end, zeroCnt, ans;
    static int[] belt;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2*N];
        visited = new boolean[2*N];
        start = 0;
        end = N - 1;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < belt.length; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            // 단계 증가
            ans++;

            // 1단계 -> 벨트 회전
            if(--start < 0) start = 2 * N -1;
            if(--end < 0) end = 2 * N - 1;

            visited[end] = false;

            // 2단계 -> 로봇 이동
            int curr = end - 1;
            for(int i = 0; i < N-2; i++) {
                if(curr < 0) curr = 2 * N - 1;

                int next = curr + 1;
                if(next >= 2 * N) next = 0;

                if(visited[curr] && !visited[next] && belt[next] > 0) {
                    if(next != end) visited[next] = true;
                    visited[curr] = false;
                    if(--belt[next] <= 0) zeroCnt++;
                }

                curr--;
            }

            // 3단계 -> 시작점에 로봇 올리기
            if(belt[start] > 0) {
                visited[start] = true;
                if(--belt[start] == 0) zeroCnt++;
            }

            // 4단계 -> 종료
            if(zeroCnt >= K) break;
        }

        System.out.println(ans);
    }
}
