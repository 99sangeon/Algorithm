package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17135Collocate (캐슬 디펜스)
public class BOJ_17135 {

    static int N, M, D, ans;
    static int[][] map;
    static int[] archer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        archer = new int[3];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution();

        System.out.println(ans);
    }

    private static void solution() {
        // 조합으로 궁수 배치 및 시뮬레이션 해보기
        comb(0, 0);
    }

    private static void comb(int start, int depth) {
        if(depth == 3) {
            simulate();
            return;
        }

        for(int i = start; i < M; i++) {
            archer[depth] = i;
            comb(i+1, depth+1);
        }
    }

    private static void simulate() {
        int[][] copyMap = copyMap();
        int tempAns = 0;

        while (!isEmpty(copyMap)) {

            List<int[]> deleteEnemy = new ArrayList<>();

            // 1번 궁수부터 3번궁수 까지 차례로 제거할 적을 정함
            for(int k = 0; k < 3; k++) {
                // 궁수 위치
                int ax = N;
                int ay = archer[k];

                int minDis = Integer.MAX_VALUE;
                int[] loc = new int[]{-1, -1};

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < M; j++) {
                        if(copyMap[i][j] == 0) continue;

                        int dis = Math.abs(ax - i) + Math.abs(ay - j);

                        // 이미 선택한 제거할 후보의 최단거리가 현재 적의 거리보다 작거나,
                        // 같으면서 더 왼쪽에 있다면 현재 위치의 적은 제거하지 않음
                        if(dis > D || dis > minDis || (dis == minDis && loc[1] < j)) continue;

                        minDis = dis;
                        loc = new int[]{i, j};
                    }
                }

                if(loc[0] > -1) {
                    deleteEnemy.add(loc);
                }
            }

            // 적 제거
            for(int i = 0; i < deleteEnemy.size(); i++) {
                int dx = deleteEnemy.get(i)[0];
                int dy = deleteEnemy.get(i)[1];

                if(copyMap[dx][dy] == 1) {
                    copyMap[dx][dy] = 0;
                    tempAns++;
                }
            }

            // 적들이 한칸 아래로 내려옴
            for(int i = N-1; i >= 0; i--) {
                for(int j = 0; j < M; j++) {
                    if(copyMap[i][j] == 0) continue;

                    copyMap[i][j] = 0;

                    if(i+1 < N) {
                        copyMap[i+1][j] = 1;
                    }
                }
            }
        }

        ans = Math.max(ans, tempAns);
    }

    private static boolean isEmpty(int[][] copyMap) {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(copyMap[i][j] == 1) return false;
            }
        }

        return true;
    }

    private static int[][] copyMap() {
        int[][] copyMap = new int[N][M];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }

        return copyMap;
    }
}
