package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17822 (원판 돌리기)
public class BOJ_17822 {
    static int N, M, T;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N+1][M];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            solution(x, d, k);
        }

        // 원판에 남아 있는 수 출력
        System.out.println(getAnswer());
    }

    private static void solution(int x, int d, int k) {
        int num = x;

        // 번호가 xi의 배수인 원판을 di방향으로 ki칸 회전시킨다. di가 0인 경우는 시계 방향, 1인 경우는 반시계 방향이다.
        while(num <= N) {
            rotate(num, d, k);
            num += x;
        }

        // 원판에 수가 남아 있으면, 인접하면서 수가 같은 것을 모두 찾는다.
        List<int[]> removeList = getRemoveList();

        if(!removeList.isEmpty()) {  // 그러한 수가 있는 경우에는 원판에서 인접하면서 같은 수를 모두 지운다.
            for(int[] loc : removeList) {
                map[loc[0]][loc[1]] = -1;
            }
        } else {  // 없는 경우에는 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
            float avg = getAvg();

            for(int i = 1; i <= N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] != -1) {
                        if(map[i][j] < avg) {
                            map[i][j]++;
                        }
                        else if(map[i][j] > avg) {
                            map[i][j]--;
                        }
                    }
                }
            }
        }
    }

    private static void rotate(int num, int d, int k) {
        // k칸 회전
        while(k-- > 0) {
            if(d == 0) {    // 시계 방향
                int tmp = map[num][M-1];

                for(int j = M-1; j > 0; j--) {
                    map[num][j] = map[num][j-1];
                }

                map[num][0] = tmp;
            } else if(d == 1){  // 반시계 방향
                int tmp = map[num][0];

                for(int j = 0; j < M-1; j++) {
                    map[num][j] = map[num][j+1];
                }

                map[num][M-1] = tmp;
            }
        }
    }

    private static List<int[]> getRemoveList() {
        ArrayList<int[]> list = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == -1) continue;

                if(j > 0) {
                    if(map[i][j] == map[i][j-1] || map[i][j] == map[i][(j+1)%M]) {
                        list.add(new int[]{i, j});
                    }
                } else {
                    if(map[i][j] == map[i][M-1] || map[i][j] == map[i][(j+1)%M]) {
                        list.add(new int[]{i, j});
                    }
                }

                if(i == 1) {
                    if(map[i][j] == map[i+1][j]) {
                        list.add(new int[]{i, j});
                    }
                } else if(i == N) {
                    if(map[i][j] == map[i-1][j]) {
                        list.add(new int[]{i, j});
                    }
                } else {
                    if(map[i][j] == map[i-1][j] || map[i][j] == map[i+1][j]) {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }

        return list;
    }

    private static float getAvg() {
        int sum = 0;
        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != -1) {
                    sum += map[i][j];
                    cnt++;
                }
            }
        }

        return (float)sum/cnt;
    }

    private static int getAnswer() {
        int sum = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != -1) {
                    sum += map[i][j];
                }
            }
        }

        return sum;
    }
}
