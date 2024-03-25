package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1062 (가르침)
public class BOJ_1062 {
    static int N, K, ans;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 5;
        arr = new int[N];

        // a, n, t, c, i 다섯 개의 문자는 반드시 선택해야함
        // 위의 다섯개 문자를 미리 제거 안하고 조합을 돌리면 2^26으로 시간 초과
        if(K < 0) {
            System.out.println(0);
            return;
        }

        for(int i = 0; i < N; i++) {
            String word = br.readLine();
            // 단어에 포함된 글자를 비트 연산을 통해 숫자로 바꿈
            arr[i] = toBit(word);
        }

        // 조합을 이용해 K개의 문자를 선택하는 경우의 수를 모두 구함
        comb('a', 0, 0);

        System.out.println(ans);
    }

    private static int toBit(String word) {
        int bit = 0;

        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if(c == 'a' || c == 'n' || c == 't' || c == 'c' || c == 'i') continue;

            bit |= 1 << c - 'a';
        }

        return bit;
    }

    private static void comb(char c, int bit, int cnt) {
        // k개의 문자를 선택 완료 -> 선택한 조합을 bit로 표현함
        if(cnt >= K) {
            ans = Math.max(ans, getAns(bit));
            return;
        }

        if(c > 'z') {
            return;
        }

        // c를 선택 안함
        comb((char)(c+1), bit, cnt);

        // c를 선택함 -> 아래 문자들이 포함될 시 선택할 수 없음
        if(c != 'a' && c != 'n' && c != 't' && c != 'c' && c != 'i'){
            bit |= 1 << c - 'a';
            comb((char)(c+1), bit, cnt + 1);
        }
    }

    private static int getAns(int bit) {
        int cnt = 0;

        for(int i = 0; i < N; i++) {
            // 선택한 문자들을 bit로 표현 -> bit
            // 선택한 문자들과 단어(arr[i])에 둘다 속하는 문자를 bit로 표현 -> temp
            int temp = arr[i] & bit;

            // 공통으로 속하는 문자들로 단어를 만들 수 있으면 결과는 0
            if((arr[i]^temp) == 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
