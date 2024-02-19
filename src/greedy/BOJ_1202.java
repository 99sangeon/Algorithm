package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1202 (보석 도둑)
public class BOJ_1202 {
    static int N, K;
    static long ans;
    static Jewel[] jewels;
    static int[] bags;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        jewels = new Jewel[N];
        bags = new int[K];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            jewels[i] = new Jewel(weight, cost);
        }

        for(int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순으로 정렬된 가방에서, 작은 무게의 가방부터 해당 가방에 넣을 수 있는 보석들 중 가장 비싼 보석을 선택
        // 그리디 알고리즘
        solution();

        System.out.println(ans);
    }

    private static void solution() {
        // 보석 무게 기준 내림차순 정렬
        Arrays.sort(jewels, (o1, o2) -> Integer.compare(o1.weight, o2.weight));
        // 가방 무게 기준 내림차순 정렬
        Arrays.sort(bags);

        PriorityQueue<Jewel> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1.cost, o2.cost));
        int jewelIdx = 0;

        for(int i = 0; i < K; i++) {
            while(jewelIdx < N) {
                // 해당 가방에 넣을 수 있는 모든 보석을 우선순위큐에 삽입
                if(jewels[jewelIdx].weight <= bags[i]) {
                    pq.add(jewels[jewelIdx]);
                    jewelIdx++;
                } else {
                    break;
                }
            }

            // 가방에 넣을 수 있는 보석 중 가장 비싼 보석을 가방에 넣음
            if(!pq.isEmpty()) {
                ans += pq.poll().cost;
            }
        }
    }

    private static class Jewel {
        int weight;
        int cost;

        private Jewel(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }
}
