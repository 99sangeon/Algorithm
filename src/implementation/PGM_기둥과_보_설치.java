package implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/60061?language=java (기둥과 보 설치)
public class PGM_기둥과_보_설치 {
    static boolean[][] pillar, floor;
    static int N;

    public int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n+1][n+1];
        floor = new boolean[n+1][n+1];
        N = n;

        for(int i = 0; i < build_frame.length; i++) {
            int y = build_frame[i][0];
            int x = build_frame[i][1];
            int a = build_frame[i][2];   // 0은 기둥, 1은 보를 나타냅니다.
            int b = build_frame[i][3];   // 0은 삭제, 1은 설치를 나타냅니다.

            if(a == 0) {
                if(b == 0) {
                    removePillar(x, y);
                } else {
                    if(canBuildPillar(x, y)) {
                        pillar[x][y] = true;
                    }
                }
            } else {
                if(b == 0) {
                    removeFloor(x, y);
                } else {
                    if(canBuildFloor(x, y)) {
                        floor[x][y] = true;
                    }
                }
            }
        }

        return getAns();
    }

    private boolean canBuildPillar(int x, int y) {
        // 기둥은 바닥 위에 있거나,
        if(x == 0) {
            return true;
        }

        // 보의 한쪽 끝 부분 위에 있거나,
        if((y > 0 && floor[x][y-1]) || floor[x][y]) {
            return true;
        }

        // 또는 다른 기둥 위에 있어야 합니다.
        if(pillar[x-1][y]) {
            return true;
        }

        return false;
    }

    private boolean checkBuild() {
        for(int i = 0; i <= N; i++) {
            for(int j = 0; j < N; j++) {
                if(pillar[i][j] && !canBuildPillar(i, j)) {
                    return false;
                }

                if(floor[i][j] && !canBuildFloor(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean canBuildFloor(int x, int y) {
        // 보는 한쪽 끝 부분이 기둥 위에 있거나,
        if(pillar[x-1][y] || pillar[x-1][y+1]) {
            return true;
        }

        // 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
        if((y > 0 && y < N && floor[x][y-1] && floor[x][y+1])) {
            return true;
        }

        return false;
    }

    private void removePillar(int x, int y) {
        pillar[x][y] = false;

        if(!checkBuild()) {
            pillar[x][y] = true;
        }
    }

    private void removeFloor(int x, int y) {
        floor[x][y] = false;

        if(!checkBuild()) {
            floor[x][y] = true;
        }
    }

    private int[][] getAns() {
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i <= N; i++) {
            for(int j = 0; j <= N; j++) {
                if(pillar[i][j]) {
                    list.add(new int[]{j, i, 0});
                }

                if(floor[i][j]) {
                    list.add(new int[]{j, i, 1});
                }
            }
        }

        int[][] ans = new int[list.size()][3];

        for(int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }

        Arrays.sort(ans, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int x1 = o1[0];
                int y1 = o1[1];
                int a1 = o1[2];
                int x2 = o2[0];
                int y2 = o2[1];
                int a2 = o2[2];

                if(x1 == x2) {
                    if(y1 == y2) {
                        return Integer.compare(a1, a2);
                    }
                    return Integer.compare(y1, y2);
                }
                return Integer.compare(x1, x2);
            }
        });

        return ans;
    }
}
