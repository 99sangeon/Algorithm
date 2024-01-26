package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17779 (게리맨더링 2)
public class BOJ_17779 {

    static int N, ans = Integer.MAX_VALUE;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        System.out.println(ans);
    }

    private static void solve() {
        // 모든 구역을 기준점으로 정해봄
        for(int x = 1; x <= N; x++) {
            for(int y = 1; y <= N; y++) {
                // 해당 구역에서 가능한 모든 d1과 d2를 정하고 구역을 나누어 봄
                for(int d1 = 1; d1 <= N; d1++) {
                    for(int d2 = 1; d2 <= N; d2++) {
                        divide(x, y, d1, d2);
                    }
                }
            }
        }
    }

    private static void divide(int x, int y, int d1, int d2) {
        // 해당 경계의 길이로 구역을 나눌 수 없을 경우 함수 즉시 종료
        if(x+d1+d2 > N || y-d1 < 1 || y+d2 > N) return;

        int[][] dividedMap = new int[N+1][N+1];

        // 경계선을 만듬
        makeBoundary(x, y, d1, d2, dividedMap);

        // 5구역 내부를 5로 채움
        fillSection5(x, d1, d2, dividedMap);

        // 모든 구역중 최소 인구, 최대 인구 차이 추출
        ans = Math.min(ans, getDiff(x, y, d1, d2, dividedMap));
    }

    private static void makeBoundary(int x, int y, int d1, int d2, int[][] dividedMap) {
        // 1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
        for(int i = x, j = y; i <= x+d1; i++, j--) {
            dividedMap[i][j] = 5;
        }
        // 2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
        for(int i = x, j = y; i <= x+d2; i++, j++) {
            dividedMap[i][j] = 5;
        }
        // 3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
        for(int i = x+d1, j = y-d1; i <= x+d1+d2; i++, j++) {
            dividedMap[i][j] = 5;
        }
        // (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
        for(int i = x+d2, j = y+d2; i <= x+d2+d1; i++, j--) {
            dividedMap[i][j] = 5;
        }
    }

    private static void fillSection5(int x, int d1, int d2, int[][] dividedMap) {
       for(int i = x+1; i < x+d1+d2; i++) {
           boolean flag = false;
           for(int j = 1; j <= N; j++) {
               if(dividedMap[i][j] == 5 && !flag) {
                   flag = true;
               } else if (dividedMap[i][j] == 5 && flag) {
                   break;
               } else if (dividedMap[i][j] == 0 && flag) {
                   dividedMap[i][j] = 5;
               }
           }
       }
    }

    private static int getDiff(int x, int y, int d1, int d2, int[][] dividedMap) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        int[] sum = new int[6];

        // 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        for(int r = 1; r < x+d1; r++) {
            for(int c = 1; c <= y; c++) {
                if(dividedMap[r][c] == 5) break;
                sum[1] += map[r][c];
            }
        }
        // 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        for(int r = 1; r <= x+d2; r++) {
            for(int c = N; c > y; c--) {
                if(dividedMap[r][c] == 5) break;
                sum[2] += map[r][c];
            }
        }
        // 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for(int r = x+d1; r <= N; r++) {
            for(int c = 1; c < y-d1+d2; c++) {
                if(dividedMap[r][c] == 5) break;
                sum[3] += map[r][c];
            }
        }
        // 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for(int r = x+d2+1; r <= N; r++) {
            for(int c = N; c >= y-d1+d2; c--) {
                if(dividedMap[r][c] == 5) break;
                sum[4] += map[r][c];
            }
        }
        // 5번 선거구
        for(int r = 1; r <= N; r++) {
            for(int c = 1; c <= N; c++) {
                if(dividedMap[r][c] == 5) sum[5] += map[r][c];
            }
        }

        for(int i = 1; i <= 5; i++) {
            min = Math.min(min, sum[i]);
            max = Math.max(max, sum[i]);
        }

        int diff = max - min;

        return diff;
    }
}
