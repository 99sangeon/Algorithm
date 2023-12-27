package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3020 (개똥벌레)
public class BOJ_3020 {

    static int N, H;
    static int[] up, down;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        up = new int[N/2];
        down = new int[N/2];

        for(int i = 0; i < N/2; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = N;
        int cnt = 0;

        for(int i = 1; i <= H; i++) {
            int temp = binarySearch(0, N/2, i, down) + binarySearch(0, N/2, H-i+1, up);

            if (temp == min) {
                cnt++;
            }

            if(temp < min) {
                cnt = 1;
                min = temp;
            }
        }

        System.out.println(min + " " + cnt);


    }

    private static int binarySearch(int left, int right, int height, int[] arr) {

        while (left < right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= height) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return arr.length - right;
    }
}
