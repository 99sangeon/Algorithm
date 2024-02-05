package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16235 (나무 재테크)
public class BOJ_16235 {
    static final int INITIAL = 5;
    static int N, M, K, ans;
    static int[][] A, map;
    static List<Integer>[][] live, dead;
    static int[] dx = {-1, 1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N+1][N+1];
        map = new int[N+1][N+1];
        live = new List[N+1][N+1];
        dead = new List[N+1][N+1];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = INITIAL;
                live[i][j] = new ArrayList<>();
                dead[i][j] = new ArrayList<>();
            }
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            live[x][y].add(z);
        }

        solution();

        ans = getLiveCnt();

        System.out.println(ans);
    }

    private static void solution() {
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }
    }

    // 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
    // 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
    // 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
    // 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
    private static void spring() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(live[i][j].size() == 0) continue;

                // 오름차순 정렬
                Collections.sort(live[i][j]);
                List<Integer> deadList = new ArrayList<>();

                for(int k = 0; k < live[i][j].size(); k++) {
                    int age = live[i][j].get(k);
                    if(map[i][j] >= age) {
                        map[i][j] -= age;
                        live[i][j].set(k, age+1);
                    } else {
                        live[i][j].remove(k--);
                        dead[i][j].add(age);
                    }
                }
            }
        }
    }

    // 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
    // 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
    private static void summer() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(int deadTree : dead[i][j]) {
                    map[i][j] += deadTree / 2;
                }

                dead[i][j].clear();
            }
        }
    }

    // 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    // 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    // 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
    private static void fall() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(int tree : live[i][j]) {
                    if(tree % 5 != 0) continue;

                    for(int k = 0; k < 8; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                        live[nx][ny].add(1);
                    }
                }
            }
        }
    }

    // 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
    // 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
    private static void winter() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                map[i][j] += A[i][j];
            }
        }
    }

    private static int getLiveCnt() {
        int cnt = 0;

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                cnt += live[i][j].size();
            }
        }

        return cnt;
    }
}
