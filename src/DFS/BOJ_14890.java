package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14890 (경사로)
public class BOJ_14890 {

    static int N, L, ans;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            // i행
            int[] road1 = new int[N];
            // i열
            int[] road2 = new int[N];

            for(int j = 0; j < N; j++) {
                road1[j] = map[i][j];
                road2[j] = map[j][i];
            }

            if(dfs(road1, 0, true)) ans++;
            if(dfs(road2, 0, true)) ans++;
        }

        System.out.println(ans);
    }

    private static boolean dfs(int[] road, int location, boolean flag) {
        if(location == N - 1) {
            return true;
        }

        int currHeight = road[location];
        int nextHeight = road[location+1];

        // 현재 칸과 다음 칸의 높이가 같으면 즉시 이동 가능
        if(currHeight == nextHeight) {
            if(dfs(road, location + 1, true)) return true;
        }

        // 현재 칸에 경사로를 놓고 경사로 끝 칸으로 이동 (오르막 경사로)
        if(flag && location + L < N && isAble(road, location, location + L - 1)) {
            if(road[location] + 1 == road[location + L] && dfs(road, location + L, true)) return true;
        }

        // 다음 칸에 경사로를 놓고 경사로 끝 칸으로 이동 (내리막 경사로)
        if(currHeight - nextHeight == 1 && location + L < N && isAble(road, location + 1, location + L)) {
            if(dfs(road, location + L, false)) return true;
        }

        return false;
    }

    // 해당 위치에 경사로 설치 가능 여부
    private static boolean isAble(int[] road, int start, int end) {
        int height = road[start];

        for(int i = start; i <= end; i++) {
            if(height != road[i]) return false;
        }

        return true;
    }
}
