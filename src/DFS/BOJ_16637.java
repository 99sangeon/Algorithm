package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// https://www.acmicpc.net/problem/16637 (괄호 추가하기)
public class BOJ_16637 {

    static int N, ans = Integer.MIN_VALUE;
    static List<String> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        String s = br.readLine();

        for (int i = 0; i < N; i++) {
            list.add(String.valueOf(s.charAt(i)));
        }

        comb(0);

        System.out.println(ans);
    }

    private static void comb(int start) {

        int result = getResult(list);
        ans = Math.max(ans, result);

        if(start >= list.size() - 1) return;

        for (int i = start; i < list.size(); i++) {
            if (list.get(i).matches("[0-9]") && i < list.size() - 1) {
                list.add(i, "(");
                list.add(i+4, ")");
                comb(i+6);
                list.remove(i);
                list.remove(i+3);
            }
        }

    }

    private static int getResult(List<String> list) {
        int result = 0;
        String op = "+";

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).matches("[0-9]")) {
                result = calculate(result, Integer.parseInt(list.get(i)), op);
            } else if (list.get(i).equals("(")) {
                int inResult = calculate(Integer.parseInt(list.get(i+1)), Integer.parseInt(list.get(i+3)), list.get(i+2));
                result = calculate(result, inResult, op);
                i += 4;
            } else {
                op = list.get(i);
            }
        }

        return result;
    }

    private static int calculate(int a, int b, String op) {
        int result = 0;

        switch (op) {
            case "-":
                result = a - b;
                break;
            case "+":
                result = a + b;
                break;
            case "*":
                result = a * b;
        }

        return result;
    }
}
