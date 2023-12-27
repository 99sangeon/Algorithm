package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// https://www.acmicpc.net/problem/1918 (후위 표기식)
public class BOJ_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;

        for(int i = 0; i < str.length(); i++) {

            char curr = str.charAt(i);

            switch (curr) {
                case '+' :
                case '-' :
                case '*' :
                case '/' :
                    while (!stack.isEmpty()) {
                        if((curr == '*' || curr == '/') && (stack.peek() == '+' || stack.peek() == '-') || stack.peek() == '(') break;
                        sb.append(stack.pop());
                    }
                    stack.push(curr);
                    break;
                case '(' :
                    stack.push(curr);
                    break;
                case ')' :
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(curr);
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.toString());
    }
}
