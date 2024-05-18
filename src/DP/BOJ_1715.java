package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// https://www.acmicpc.net/problem/1715 (카드 정렬하기)
public class BOJ_1715 {
    static int N;
    static PriorityQueue<Integer> pq;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            int sum = a + b;
            ans += sum;
            pq.add(sum);
        }

        System.out.println(ans);
    }
}
