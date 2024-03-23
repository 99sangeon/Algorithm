package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2493 (탑)
public class BOJ_2493 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = solution();

        System.out.println(sb);
    }

    private static StringBuilder solution() {
        Stack<int[]> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= N; i++) {
            int height = arr[i];

            while(!stack.isEmpty()) {
                if(stack.peek()[0] <= height) {
                    stack.pop();
                } else {
                    sb.append(stack.peek()[1]).append(" ");
                    break;
                }
            }

            if(stack.isEmpty()) {
                sb.append(0).append(" ");
            }

            stack.push(new int[]{height, i});
        }

        return sb;
    }
}
