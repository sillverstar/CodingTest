import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[][] map;
    static boolean[] dessert;
    static int maxCnt;

    // 대각선 4방향 (0->1->2->3 순서)
    // 0: 오른쪽 아래, 1: 왼쪽 아래, 2: 왼쪽 위, 3: 오른쪽 위
    static int[] dr = { 1,  1, -1, -1};
    static int[] dc = { 1, -1, -1,  1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            maxCnt = -1;
            map = new int[N][N];
            dessert = new boolean[101]; // 1~100
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // dfs 호출
            for (int sr = 0; sr < N-2; sr++) {
                for (int sc = 1; sc < N-1; sc++) {
                    dessert[map[sr][sc]] = true;

                    dfs(sr, sc, sr, sc, 0, 1);

                    dessert[map[sr][sc]] = false;
                }
            }
            sb.append('#').append(t).append(' ').append(maxCnt).append('\n');
        }
        System.out.print(sb);
    }

    // turnCnt 제거한 버전
    private static void dfs(int r, int c, int sr, int sc, int dir, int count) {

        // 선택지 2개: 직진(dir) 또는 꺾기(dir+1)
    	// 0-1-2-3 다른 방향으로 못 감
        for (int ndir = dir; ndir <= dir + 1; ndir++) {
            if (ndir > 3) break; // 방향 범위(0~3)

            int nr = r + dr[ndir];
            int nc = c + dc[ndir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            // 종료 조건
            if (nr == sr && nc == sc) {
                if (count >= 4) { // 최소 루프 길이
                    maxCnt = Math.max(maxCnt, count);
                }
                continue;
            }

            if (dessert[map[nr][nc]]) continue; // 디저트 중복이면 불가

            dessert[map[nr][nc]] = true;
            dfs(nr, nc, sr, sc, ndir, count + 1);
            dessert[map[nr][nc]] = false;
        }
    }

}