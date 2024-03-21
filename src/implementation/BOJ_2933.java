package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2933 (미네랄)
public class BOJ_2933 {
    static int R, C, N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int dir = 0;   // 0 -> 왼쪽, 1 -> 오른쪽

        for(int i = 0; i < N; i++) {
            int height = Integer.parseInt(st.nextToken());
            solution(height, dir);
            dir = (dir + 1) % 2;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void solution(int height, int dir) {
        // 막대를 던져서 미네랄을 부숨
        throwStick(height, dir);

        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == '.' || visited[i][j]) continue;

                // 클러스트에 포함된 미네랄의 위치
                List<int[]> list = new ArrayList<>();

                // 해당 클러스터가 공중에 떠 있다면 아래로 떨어짐
                if(bfs(i, j, list)) {
                    pullDown(list);
                    return;
                }
            }
        }
    }

    private static void throwStick(int height, int dir) {
        height = R - height;

        if(dir == 0) {
            for(int j = 0; j < C; j++) {
                if(map[height][j] == 'x') {
                    map[height][j] = '.';
                    break;
                }
            }
        } else {
            for(int j = C-1; j >= 0; j--) {
                if(map[height][j] == 'x') {
                    map[height][j] = '.';
                    break;
                }
            }
        }
    }

    private static boolean bfs(int sx, int sy, List<int[]> list) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});
        visited[sx][sy] = true;

        boolean flag = true;

        while(!q.isEmpty()) {
            int[] poll = q.poll();
            int cx = poll[0];
            int cy = poll[1];
            list.add(poll);

            // 해당 미네랄이 바닥에 붙어 있다면 해당 클러스터는 공중에 떠 있지 않음
            if(cx == R-1) {
                flag = false;
            }

            for(int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny] || map[nx][ny] == '.') continue;

                int[] next = new int[]{nx, ny};
                q.add(next);
                visited[nx][ny] = true;
            }
        }

        // 해당 클러스터는 공중에 떠 있음
        return flag;
    }

    private static void pullDown(List<int[]> list) {
        // 해당 클러스터의 각 열별로 가장 바닥과 가까운 높이
        int[] max = new int[C];
        Arrays.fill(max, -1);

        for(int[] loc : list) {
            max[loc[1]] = Math.max(max[loc[1]], loc[0]);
        }

        // 클러스터가 떨어지는 높이
        int move = Integer.MAX_VALUE;

        for(int j = 0; j < C; j++) {
            if(max[j] < 0) continue;

            int temp = 0;

            for(int i = max[j]+1; i < R; i++) {
                if(map[i][j] == '.') {
                    temp++;
                } else {
                    break;
                }
            }

            move = Math.min(move, temp);
        }

        // 아래로 떨어뜨리기 위해 높이가 낮은 순으로 정렬
        list.sort((o1, o2) -> -Integer.compare(o1[0], o2[0]));

        for(int[] loc : list) {
            map[loc[0]][loc[1]] = '.';
            map[loc[0]+move][loc[1]] = 'x';
        }
    }
}
