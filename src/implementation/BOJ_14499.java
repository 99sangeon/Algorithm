package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14499 (주사위 굴리기)
public class BOJ_14499 {

    static int N, M, cx, cy, K;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};
    static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dice = new int[7];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            rollDice(dir);
        }

        System.out.println(ans);
    }

    private static void rollDice(int dir) {

        int nx = cx + dx[dir];
        int ny = cy + dy[dir];

        if(nx < 0 || nx >= N || ny < 0 || ny >= M) return;

        cx = nx;
        cy = ny;

        int[] copyDice = Arrays.copyOf(dice, dice.length);

        switch (dir) {
            case 1: // 동
                dice[1] = copyDice[4];
                dice[3] = copyDice[1];
                dice[4] = copyDice[6];
                dice[6] = copyDice[3];
                break;
            case 2: // 서
                dice[1] = copyDice[3];
                dice[3] = copyDice[6];
                dice[4] = copyDice[1];
                dice[6] = copyDice[4];
                break;
            case 3: // 북
                dice[1] = copyDice[5];
                dice[2] = copyDice[1];
                dice[5] = copyDice[6];
                dice[6] = copyDice[2];
                break;
            case 4: // 남
                dice[1] = copyDice[2];
                dice[2] = copyDice[6];
                dice[5] = copyDice[1];
                dice[6] = copyDice[5];
                break;
        }

        if(map[cx][cy] == 0) {
          map[cx][cy] = dice[6];
        } else {
            dice[6] = map[cx][cy];
            map[cx][cy] = 0;
        }

        ans.append(dice[1]).append("\n");
    }
}
