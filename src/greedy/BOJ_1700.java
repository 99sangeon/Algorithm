package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1700
public class BOJ_1700 {
    static int N, K;
    static List<Integer> list;
    static PriorityQueue<Integer> multiTap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        multiTap = new PriorityQueue<>((o1, o2) -> {
            int o1Idx = list.indexOf(o1);
            int o2Idx = list.indexOf(o2);

            if(o1Idx == -1) {
                return -1;
            }

            if(o2Idx == -1) {
                return 1;
            }

            return Integer.compare(o2Idx, o1Idx);
        });

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int ans = solution();

        System.out.println(ans);
    }

    private static int solution() {
        int ans = 0;

        while(!list.isEmpty()) {
            int curr = list.remove(0);

            // 여유 공간이 있으면 해당 전기제품 멀티탭에 꽂음
            if(multiTap.size() < N && !multiTap.contains(curr)) {
                multiTap.add(curr);
                continue;
            }

            if(multiTap.contains(curr)) { // 이미 해당 전기 제품이 꽂혀져 있다면 새로 꼽을 필요는 없지만 pq를 초기화 시켜줌
                multiTap.remove(curr);
            } else {  // 해당 전기 제품이 꽂혀져있지 않다면 다른 전기 제품을 뽑고 해당 전기 제품을 꽂아줌
                multiTap.poll();
                ans++;
            }

            multiTap.add(curr);
        }

        return ans;
    }
}
