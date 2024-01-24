package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17140 (이차원 배열과 연산)
public class BOJ_17140 {

    static int R, C, K, ans;
    static final int N = 100;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1][N+1];

        for(int i = 1; i <= 3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(ans);
    }

    private static void solve() {
        int rowCnt = 3;
        int colCnt = 3;

        while (true) {
            if(arr[R][C] == K) {
                return;
            }
            if(ans == 100) {
                ans = -1;
                return;
            }

            // 행의 개수가 열의 개수보다 같거나 크면 R연산, 아니면 C연산
            if(rowCnt >= colCnt) {
                // R연산을 하면 col의 개수가 변할 수도 있음 -> 각 행의 원소가 많아지면 col의 개수가 증가
                colCnt = operatorR(rowCnt, colCnt);
            } else {
                // C연산을 하면 row의 개수가 변할 수도 있음 -> 각 열의 원소가 많아지면 row의 개수가 증가
                rowCnt = operatorC(rowCnt, colCnt);
            }

            ans++;
        }
    }

    private static int operatorR(int rowCnt, int colCnt) {

        int max = 0;

        // 모든 행에 대하여 R연산
        for(int i = 1; i <= rowCnt; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            // 행의 모든 원소 중복 제거 및 개수 파악
            for(int j = 1; j <= colCnt; j++) {
                if(arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            // 새로운 행 정렬
            List<Integer> keyList = new ArrayList<>(map.keySet());
            Collections.sort(keyList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int o1Cnt = map.get(o1);
                    int o2Cnt = map.get(o2);

                    // o1과 o2의 개수가 다르면 개수로 정렬
                    if(o1Cnt != o2Cnt) {
                        return Integer.compare(o1Cnt, o2Cnt);
                    }

                    // o1과 o2의 개수가 같으면 o1과 o2로 정렬
                    return Integer.compare(o1, o2);
                }
            });

            // 최대 행 사이즈 갱신
            int size = keyList.size() * 2;
            max = Math.max(max, size);

            // 새로운 행 삽입
            for(int j = 0; j < keyList.size(); j++) {
                if(j >= 50) break;

                arr[i][j*2+1] = keyList.get(j);
                arr[i][j*2+2] = map.get(keyList.get(j));
            }

            // 새로운 행의 크기가 기존 행의 크기보다 작을 큰 부분 0으로 초기화
            if(size < colCnt) {
                for(int j = size+1; j <= colCnt; j++) {
                    arr[i][j] = 0;
                }
            }
        }

        return max;
    }

    private static int operatorC(int rowCnt, int colCnt) {
        int max = 0;

        // 모든 열에 대하여 C연산
        for(int j = 1; j <= colCnt; j++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            // 열의 모든 원소 중복 제거 및 개수 파악
            for(int i = 1; i <= rowCnt; i++) {
                if(arr[i][j] == 0) continue;
                map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
            }

            // 새로운 열 정렬
            List<Integer> keyList = new ArrayList<>(map.keySet());
            Collections.sort(keyList, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    int o1Cnt = map.get(o1);
                    int o2Cnt = map.get(o2);

                    // o1과 o2의 개수가 다르면 개수로 정렬
                    if(o1Cnt != o2Cnt) {
                        return Integer.compare(o1Cnt, o2Cnt);
                    }

                    // o1과 o2의 개수가 같으면 o1과 o2로 정렬
                    return Integer.compare(o1, o2);
                }
            });

            // 최대 열 사이즈 갱신
            int size = keyList.size() * 2;
            max = Math.max(max, size);

            // 새로운 열 삽입
            for(int i = 0; i < keyList.size(); i++) {
                if(i >= 50) break;

                arr[i*2+1][j] = keyList.get(i);
                arr[i*2+2][j] = map.get(keyList.get(i));
            }

            // 새로운 열의 크기가 기존 열의 크기보다 작을 큰 부분 0으로 초기화
            if(size < rowCnt) {
                for(int i = size+1; i <= rowCnt; i++) {
                    arr[i][j] = 0;
                }
            }
        }

        return max;
    }
}
