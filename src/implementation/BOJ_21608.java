package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/21608 (상어 초등학교)
public class BOJ_21608 {

    static int N;
    static int[][] map;
    static boolean[][] students;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] score = {0, 1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        students = new boolean[N*N+1][N*N+1];
        for(int i = 0; i < N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            for(int j = 0; j < 4; j++) {
                int like = Integer.parseInt(st.nextToken());
                students[num][like] = true;
            }

            placeStudent(num);
        }

        int ans = getScore();

        System.out.println(ans);
    }

    private static void placeStudent(int num) {
        List<int[]> locations = new ArrayList<>();
        int max = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                // 해당칸이 비어있지 않다면 다른 칸 탐색
                if(map[i][j] > 0) continue;

                // 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
                int cnt = 0;
                int emptyCnt = 0;
                List<int[]> temp = new ArrayList<>();
                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if(students[num][map[nx][ny]]) cnt++;
                    if(map[nx][ny] == 0) emptyCnt++;

                    temp.add(new int[]{i, j, emptyCnt});
                }

                if(cnt < max) continue;

                if (cnt > max) {
                    max = cnt;
                    locations.clear();
                }

                for(int[] t : temp) {
                    locations.add(t);
                }
            }
        }

        // 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        // 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
        Collections.sort(locations, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int o1X = o1[0];
                int o1Y = o1[1];
                int o1EmptyCnt = o1[2];
                int o2X = o2[0];
                int o2Y = o2[1];
                int o2EmptyCnt = o2[2];

                if(o1EmptyCnt == o2EmptyCnt) {
                    if(o1X == o2X) {
                        return Integer.compare(o1X, o2X);
                    } else {
                        return Integer.compare(o1Y, o2Y);
                    }
                } else {
                    return -Integer.compare(o1EmptyCnt, o2EmptyCnt);
                }
            }
        });

        int[] finalLocation = locations.get(0);
        map[finalLocation[0]][finalLocation[1]] = num;
    }

    private static int getScore() {
        int scoreSum = 0;

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int cnt = 0;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                    if(students[map[i][j]][map[nx][ny]]) cnt++;
                }

                scoreSum += score[cnt];
            }
        }

        return scoreSum;
    }
}
