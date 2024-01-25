package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15685 (드래곤 커브)
public class BOJ_15685 {

    static int N, ans;
    static final int SIZE = 100;
    static boolean[][] map;
    static int[] dRow = {0, -1, 0, 1};
    static int[] dCol = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new boolean[SIZE+1][SIZE+1];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());

            draw(y, x, dir, gen);
        }

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) {
                    ans++;
                }
            }
        }

        System.out.println(ans);
    }

    private static void draw(int row, int col, int dir, int gen) {
        map[row][col] = true;

        List<Integer> directions = new ArrayList<>();
        directions.add(dir);

        while (gen-- > 0) {
            for(int i = directions.size()-1; i >= 0; i--) {
                directions.add((directions.get(i) + 1) % 4);
            }
        }

        for(int i = 0; i < directions.size(); i++) {
            row += dRow[directions.get(i)];
            col += dCol[directions.get(i)];
            map[row][col] = true;
        }
    }

}
