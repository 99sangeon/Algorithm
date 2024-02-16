package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2143 (두 배열의 합)
public class BOJ_2143 {
    static int T, N, M;
    static long ans;
    static int[] arr1;
    static int[] arr2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        arr1 = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr1[j];
                list.add(sum);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < M; i++) {
            int sum = 0;
            for (int j = i; j < M; j++) {
                sum += arr2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int num : list) {
            // 투포인터로 두 리스트를 탐색하며 정답을 찾을 수 있지만, 더 간단하게 해쉬맵으로 찾고자 하는 값의 개수를 빠르게 찾을 수 있음
            ans += map.getOrDefault(T - num, 0);
        }
    }
}
