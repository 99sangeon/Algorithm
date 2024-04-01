package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15898 (피아의 아틀리에 ~신비한 대회의 연금술사~)
public class BOJ_15898 {
    static final int MATERIALS_SIZE = 4, MAP_SIZE = 5;
    static int N, ans;
    static int[][][] materials_quality;
    static char[][][] materials_color;
    static boolean[] visited;
    static int[] lx = {0, 1, 0, 1};
    static int[] ly = {0, 0, 1, 1};
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        materials_quality = new int[N][MATERIALS_SIZE][MATERIALS_SIZE];
        materials_color = new char[N][MATERIALS_SIZE][MATERIALS_SIZE];
        visited = new boolean[N];

        for(int k = 0; k < N; k++) {
            // 재료 품질 입력
            for(int i = 0; i < MATERIALS_SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < MATERIALS_SIZE; j++) {
                    materials_quality[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 재료 색상 입력
            for(int i = 0; i < MATERIALS_SIZE; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < MATERIALS_SIZE; j++) {
                    materials_color[k][i][j] = st.nextToken().charAt(0);
                }
            }
        }

        int[][] mapQuality = new int[MAP_SIZE][MAP_SIZE];
        char[][] mapColor = new char[MAP_SIZE][MAP_SIZE];

        for(int i = 0; i < MAP_SIZE; i++) {
            Arrays.fill(mapColor[i], 'W');
        }

        solution(0, mapQuality, mapColor);

        System.out.println(ans);
    }

    private static void solution(int cnt, int[][] mapQuality, char[][] mapColor) {
        if(cnt >= 3) {
            ans = Math.max(ans, calcPoint(mapQuality, mapColor));
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]){
                visited[i] = true;

                // 4개의 위치
                for(int l = 0; l < 4; l++) {
                    // 4개의 방향
                    for(int d = 0; d < 4; d++) {
                        int[][] copyMapQuality = getCopyMapQuality(mapQuality);
                        char[][] copyMapColor = getCopyMapColor(mapColor);

                        insertMaterialsInCopyMap(i, l, copyMapQuality, copyMapColor);
                        solution(cnt+1, copyMapQuality, copyMapColor);
                        // 반시계 방향으로 1회전
                        rotateMaterials(i);
                    }
                }

                visited[i] = false;
            }
        }
    }

    private static int[][] getCopyMapQuality(int[][] mapQuality) {
        int[][] copyMapQuality = new int[MAP_SIZE][MAP_SIZE];

        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                copyMapQuality[i][j] = mapQuality[i][j];
            }
        }

        return copyMapQuality;
    }

    private static char[][] getCopyMapColor(char[][] mapColor) {
        char[][] copyMapColor = new char[MAP_SIZE][MAP_SIZE];

        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                copyMapColor[i][j] = mapColor[i][j];
            }
        }

        return copyMapColor;
    }

    private static void rotateMaterials(int materialsNum) {
        int[][] quality = materials_quality[materialsNum];
        char[][] color = materials_color[materialsNum];

        for(int i = 0; i < 3; i++) {
            rotate(0, 3,  quality, color);
        }

        rotate(1, 2, quality, color);
    }

    private static void rotate(int start, int end, int[][] quality, char[][] color) {
        int cx = start;
        int cy = start;
        int dir = 0;
        int tempQuality = quality[start][start];
        char tempColor = color[start][start];

        while(!(cx == start+1 && cy == start)) {
            int nx = cx + dx[dir];
            int ny = cy + dy[dir];

            if(nx < start || nx > end || ny < start || ny > end) {
                dir++;
                continue;
            }

            quality[cx][cy] = quality[nx][ny];
            color[cx][cy] = color[nx][ny];

            cx = nx;
            cy = ny;
        }

        quality[cx][cy] = tempQuality;
        color[cx][cy] = tempColor;
    }

    private static void insertMaterialsInCopyMap(int materialsNum, int loc, int[][] copyMapQuality, char[][] copyMapColor) {
        int[][] quality = materials_quality[materialsNum];
        char[][] color = materials_color[materialsNum];

        // 재료가 위치하지 않는 가마의 격자칸은 아무런 변화가 생기지 않는다.
        // 재료가 위치한 가마의 격자칸에 있는 품질과 원소값은 바뀔 수 있다.
        for(int i = 0; i < MATERIALS_SIZE; i++) {
            for(int j = 0; j < MATERIALS_SIZE; j++) {
                // 격자의 품질은 재료의 효능이 더해진다. 더한 뒤의 값이 음수인 경우 0으로, 9 초과인 경우 9로 바뀐다.
                copyMapQuality[i+lx[loc]][j+ly[loc]] += quality[i][j];

                if(copyMapQuality[i+lx[loc]][j+ly[loc]] < 0) {
                    copyMapQuality[i+lx[loc]][j+ly[loc]] = 0;
                } else if (copyMapQuality[i+lx[loc]][j+ly[loc]] > 9) {
                    copyMapQuality[i+lx[loc]][j+ly[loc]] = 9;
                }

                // 격자의 색은 재료의 원소가 흰색인 경우 그대로, 아닌 경우 재료의 원소와 같은 색으로 칠해진다.
                if(color[i][j] != 'W') {
                    copyMapColor[i+lx[loc]][j+ly[loc]] = color[i][j];
                }
            }
        }
    }

    private static int calcPoint(int[][] mapQuality, char[][] mapColor) {
        int R = 0, B = 0, G = 0, Y = 0;

        for(int i = 0; i < MAP_SIZE; i++) {
            for(int j = 0; j < MAP_SIZE; j++) {
                switch(mapColor[i][j]) {
                    case 'R':
                        R += mapQuality[i][j];
                        break;
                    case 'B':
                        B += mapQuality[i][j];
                        break;
                    case 'G':
                        G += mapQuality[i][j];
                        break;
                    case 'Y':
                        Y += mapQuality[i][j];
                }
            }
        }

        return (7 * R) + (5 * B) + (3 * G) + (2 * Y);
    }
}
