package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2467 (용액)
public class BOJ_2467 {

    static int N, ans1, ans2;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;

        while (left < right) {
            int lValue = arr[left];
            int rValue = arr[right];
            int sum = lValue + rValue;

            if(sum < 0) left++;
            else if (sum > 0) right--;

            sum = Math.abs(sum);
            if(sum < min) {
                ans1 = lValue;
                ans2 = rValue;
                min = sum;
            }

            if(sum == 0) break;
        }

        System.out.println(ans1 + " " + ans2);
    }
}
