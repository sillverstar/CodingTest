import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] map;
    static long[][][] dp; // dp[r][c][dir]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N][3];

        // 시작 상태: (0,1)에 가로 상태로 파이프 끝이 있음
        dp[0][1][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 2; c < N; c++) {
                if (map[r][c] == 1) continue;

                // 가로로 오는 경우
                dp[r][c][0] += dp[r][c - 1][0] + dp[r][c - 1][2];

                // 세로로 오는 경우
                if (r - 1 >= 0) {
                    dp[r][c][1] += dp[r - 1][c][1] + dp[r - 1][c][2];
                }

                // 대각선으로 오는 경우
                if (r - 1 >= 0 && map[r - 1][c] == 0 && map[r][c - 1] == 0) {
                    dp[r][c][2] += dp[r - 1][c - 1][0] + dp[r - 1][c - 1][1] + dp[r - 1][c - 1][2];
                }
            }
        }

        long answer = dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2];
        System.out.println(answer);
    }
}