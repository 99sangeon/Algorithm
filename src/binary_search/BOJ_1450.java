package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1450 (냅색문제)
public class BOJ_1450 {

    static int N, C, ans = 1;
    static int[] arr;
    static List<Integer> listA, listB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];
        listA = new ArrayList<>();
        listB = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        // N이 최대 30이므로 시간복잡도를 고려하여 절반 씩 나누어 조합을 탐색함
        comb(0, N/2, 0, listA);
        comb(N/2, N, 0, listB);

        ans += listA.size();
        ans += listB.size();

        Collections.sort(listB);

        for(int w : listA) {
            // listB에서 C - sum 보다 작거나 같은 원소의 개수를 찾음
            // 즉, 시간복잡도는 -> 2^15 * log2^15
            ans += binarySearch(C - w);
        }
    }

    private static void comb(int start, int end, int weight, List<Integer> list) {
        for(int i = start; i < end; i++) {
            if(weight + arr[i] > C) continue;

            list.add(weight + arr[i]);
            comb(i+1, end, weight + arr[i], list);
        }
    }

    private static int binarySearch(int target) {
        int left = 0;
        int right = listB.size() - 1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(listB.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
