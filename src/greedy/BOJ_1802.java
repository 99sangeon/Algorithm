package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1802 (종이 접기)
public class BOJ_1802 {
    static int T;
    static char[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for(int t = 0; t < T; t++) {
            arr = br.readLine().toCharArray();

            if(solution(0, arr.length - 1)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }


        }

        System.out.println(sb);
    }

    private static boolean solution(int left, int right) {
        boolean flag = true;

        if(left >= right) {
            return flag;
        }

        int mid = (left + right) / 2;
        int l = left;
        int r = right;

        while (l < r) {
            if(arr[l] == arr[r]) {
                flag = false;
                break;
            }
            l++;
            r--;
        }

        if(!flag) {
            return flag;
        } else {
            return solution(left, mid - 1) && solution(mid + 1, right);
        }
    }
}
