package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14719 (빗물)
public class BOJ_14719 {
    static int H, W;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        int ans = 0;

        for(int i = 1; i < W-1; i++) {
            int lMax = getLeftMax(i);
            int rMax = getRightMax(i);

            // 현재 위치(i)의 왼쪽과 오른쪽 영역에 현재 위치의 블록보다 더 높은 블록이 있음 -> 현재 위치에 빗물이 고일 수 있음
            // 빗물이 고이는 양 = min(왼쪽 최대 높이, 오른쪽 최대 높이) - 현재 위치의 높이
            if(lMax > arr[i] && rMax > arr[i]) {
                ans += Math.min(lMax, rMax) - arr[i];
            }
        }

        return ans;
    }

    private static int getLeftMax(int loc) {
        int max = 0;

        for(int i = 0; i < loc; i++) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }

    private static int getRightMax(int loc) {
        int max = 0;

        for(int i = loc+1; i < W; i++) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }

}
