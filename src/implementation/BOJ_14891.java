package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14891 (톱니바퀴)
public class BOJ_14891 {

    static List<Integer>[] gears;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears = new List[5];

        for(int i = 1; i <= 4; i++) {
            String s = br.readLine();
            gears[i] = new ArrayList<>();

            for(int j = 0; j < s.length(); j++) {
                gears[i].add(s.charAt(j) - '0');
            }
        }

        int K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            visited = new boolean[5];

            rotateGear(num, dir);
        }

        int ans = 0;
        int score = 1;

        for(int i = 1; i <= 4; i++) {
            if(gears[i].get(0) == 1) ans += score;
            score *= 2;
        }

        System.out.println(ans);
    }

    private static void rotateGear(int num, int dir) {

        visited[num] = true;

        List<Integer> gear = gears[num];
        int nextDir = (dir == 1) ? -1 : 1;

        // 오른쪽 톱니바퀴 회전
        if(num < 4) {
            if(gear.get(2) != gears[num+1].get(6) && !visited[num+1]) {
                rotateGear(num + 1, nextDir);
            }
        }

        // 왼쪽 톱니바퀴 회전
        if(num > 1) {
            if(gear.get(6) != gears[num-1].get(2) && !visited[num-1]) {
                rotateGear(num - 1, nextDir);
            }
        }

        if(dir == 1) {  // 시계방향 회전
            int remove = gear.remove(7);
            gear.add(0, remove);
        } else {        // 반시계방향 회전
            int remove = gear.remove(0);
            gear.add(remove);
        }
    }


}
