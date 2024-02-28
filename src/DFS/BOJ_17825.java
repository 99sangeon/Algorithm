package DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17825 (주사위 윷놀이)
public class BOJ_17825 {
    static final int N = 10, END = 21, BLOCK_CNT = 33;
    static int ans;
    static int[] arr, score, horse;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[N];
        score = new int[BLOCK_CNT];
        visited = new boolean[BLOCK_CNT];
        horse = new int[4];

        setScore();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        dfs(0, 0);

        System.out.println(ans);
    }

    private static void setScore() {
        // 0 -> 시작점, 21 -> 도착점
        for(int i = 1; i <= 20; i++) {
            score[i] = i * 2;
        }

        score[22] = 13; score[23] = 16; score[24] = 19;
        score[25] = 25; score[26] = 30; score[27] = 35;
        score[28] = 22; score[29] = 24;
        score[30] = 28; score[31] = 27; score[32] = 26;
    }

    private static void dfs(int s, int depth) {
        ans = Math.max(ans, s);

        if (depth == 10) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            // 이미 도착한 말이라면
            if(horse[i] == END) continue;

            // 방문할 칸이 도착 칸이 아니면서 그 칸에 말이 이미 있다면
            int next = move(horse[i], arr[depth]);
            if(next != END && visited[next]) continue;

            int curr = horse[i];

            horse[i] = next;
            visited[curr] = false;
            visited[next] = true;
            dfs(s + score[next], depth + 1);
            horse[i] = curr;
            visited[curr] = true;
            visited[next] = false;
        }
    }

    private static int move(int loc, int cnt) {
        // 해당 위치의 경우 두 갈래로 나뉘기 때문에 해당위치에서 움직인다면 파란색 루트를 따라가야함
        switch(loc) {
            case 5:
                loc = 22;
                cnt--;
                break;
            case 10:
                loc = 28;
                cnt--;
                break;
            case 15:
                loc = 30;
                cnt--;
                break;
        }

        // 말을 움직임
        while(cnt-- > 0) {
            switch(loc) {
                case 29:
                case 32:
                    loc = 25;
                    break;
                case 27:
                    loc = 20;
                    break;
                case END:
                    return END;
                default:
                    loc++;
            }
        }

        return loc;
    }
}
