package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/2504 (괄호의 값)
public class BOJ_2504 {
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = br.readLine().toCharArray();

        System.out.println(solution());
    }

    private static int solution() {
        Stack<Character> stack = new Stack<>();
        int ans = 0;
        int tmp = 1;

        for(int i = 0; i < arr.length; i++) {
            char curr = arr[i];

            // 분배법칙을 이용하여 풀어야 함 ->  2*(2+3) = 2*2 + 2*3
            switch(curr) {
                case '(':
                    tmp *= 2;
                    stack.push(curr);
                    break;
                case '[':
                    tmp *= 3;
                    stack.push(curr);
                    break;
                case ')':
                    if(stack.isEmpty() || stack.peek() != '(') return 0;

                    if(arr[i-1] == '(') {
                        ans += tmp;
                    }
                    tmp /= 2;
                    stack.pop();
                    break;
                case ']':
                    if(stack.isEmpty() || stack.peek() != '[') return 0;

                    if(arr[i-1] == '[') {
                        ans += tmp;
                    }
                    tmp /= 3;
                    stack.pop();
            }
        }

        if(!stack.isEmpty()) {
            return 0;
        }

        return ans;
    }

}
