package divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10830 (행렬 제곱)
public class BOJ_10830 {
    static int N;
    static long B;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        matrix = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                matrix[i][j] %= 1000;
            }
        }

        int[][] ans = divide(B);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[][] divide(long b) {
        if(b == 1) return matrix;

        // 분할
        int[][] temp = divide(b / 2);
        // 정복 (곱셈)
        temp = conquer(temp, temp);

        if(b % 2 == 1) {
            temp = conquer(temp, matrix);
        }

        return temp;
    }

    private static int[][] conquer(int[][] matrix1, int[][] matrix2) {
        int[][] temp = new int[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    temp[i][j] += matrix1[i][k] * matrix2[k][j];
                    temp[i][j] %= 1000;
                }
            }
        }

        return temp;
    }
}
