package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13397 (구간 나누기 2)
public class BOJ_13397 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int max = 0;

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int ans = solution(0, max);

        System.out.println(ans);
    }

    private static int solution(int left, int right) {
        while(left < right) {
            int mid = (left + right) / 2;

            int sectionCnt = findSectionCnt(mid);

            if(sectionCnt > M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int findSectionCnt(int target) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int sectionCnt = 1;

        for(int i = 0; i < N; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);

            if(max - min > target) {
                min = Integer.MAX_VALUE;
                max = 0;
                sectionCnt++;
                i--;
            }
        }

        return sectionCnt;
    }
}
