package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2632 (피자 판매)
public class BOJ_2632 {
    static int target, n, m, ans;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        A = new int[m];
        B = new int[n];

        for(int i = 0; i < m; i++) {
            A[i] = Integer.parseInt(br.readLine());

        }

        for(int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(br.readLine());
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        // A 피자로 만들 수 있는 피자 모든 피자 사이즈
        comb(m, A, listA);
        // B 피자로 만들 수 있는 피자 모든 피자 사이즈
        comb(n, B, listB);

        listA.sort((o1, o2) -> Integer.compare(o1, o2));
        listB.sort((o1, o2) -> Integer.compare(o1, o2));

        // 투 포인터 알고리즘을 이용해 target의 개수를 찾아냄
        twoPointer(listA, listB);
    }

    private static void comb(int size, int[] arr, List<Integer> list) {
        for (int i = 0; i < size; i++) {
            int sum = 0;
            int j = i;
            int cnt = size - 1;

            if(i == 0) {
                cnt++;
            }

            while(cnt-- > 0) {
                sum += arr[j++];
                if (sum >= target) {
                    // 손님이 구매하고자 하는 피자크기와 같을 경우 정답 +1
                    if (sum == target) {
                        ans++;
                    }
                    break;
                }

                list.add(sum);

                if(j == size) {
                    j = 0;
                }
            }
        }
    }

    private static void twoPointer(List<Integer> listA, List<Integer> listB) {
        int left = 0;
        int right = listB.size() - 1;

        while (left < listA.size() && right >= 0) {
            int a = listA.get(left);
            int b = listB.get(right);
            int sum = a + b;

            if(sum == target) {
                int aCnt = 0;
                int bCnt = 0;

                while (left < listA.size() && listA.get(left) == a) {
                    aCnt++;
                    left++;
                }

                while (right >= 0 && listB.get(right) == b) {
                    bCnt++;
                    right--;
                }

                ans += aCnt * bCnt;

            } else if(sum < target) {
                left++;
            } else {
                right--;
            }
        }
    }
}
