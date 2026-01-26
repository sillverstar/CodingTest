import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); // 가로
        int n = Integer.parseInt(st.nextToken()); // 세로
        int h = Integer.parseInt(st.nextToken()); // 높이

        int[][][] box = new int[h][n][m]; // 접근 순서 h(높이) -> n(세로) -> m(가로)

        // 상하좌우 -> 위, 아래, (왼쪽, 오른쪽, 앞, 뒤): 3개로 분리
        int[] dh = {-1, 1, 0, 0, 0, 0};  // 위 아래
        int[] dr = {0, 0, -1, 1, 0, 0};  // 상 하
        int[] dc = {0, 0, 0, 0, -1, 1};  // 좌 우


        Queue<int[]> queue = new LinkedList<>();

        // 입력 받으면서 익은 토마토(1) 위치를 전부 큐에 넣기 (멀티 소스)
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    // 1: 익음, 0: 안 익음, -1: 비어있음
                    box[k][i][j] = Integer.parseInt(st.nextToken());

                    // 익어있는 토마토(==1; BFS 시작점)면 모두 큐에 넣음
                    if (box[k][i][j] == 1) {
                        queue.add(new int[]{k, i, j});
                    }
                }
            }
        }
//
//        System.out.println(Arrays.deepToString(box));
//        System.out.println("큐 상태:");
//        for (int[] p : queue) {
//            System.out.println(Arrays.toString(p));
//        }

        // BFS
        while (!queue.isEmpty()) { // queue가 내부가 빌 때까지
            int[] now = queue.poll();
            int z = now[0];
            int x = now[1];
            int y = now[2];

            for (int d = 0; d < 6; d++) {
                int nz = z + dh[d];
                int nx = x + dr[d];
                int ny = y + dc[d];

                // 범위 체크
                if (nz < 0 || nz >= h || nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 안 익은 토마토(0)만 익히기
                if (box[nz][nx][ny] == 0) {
                    box[nz][nx][ny] = box[z][x][y] + 1; // 날짜 기록
                    queue.add(new int[]{nz, nx, ny});
                }
                //System.out.println(Arrays.deepToString(box));
            }

        }

        // 최종
        int maxVal = 0;
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (box[k][i][j] == 0) { // 안 익은 토마토 남아있으면 무조건 -1
                        System.out.println(-1);
                        return;
                    }
                    maxVal = Math.max(maxVal, box[k][i][j]);
                }
            }
        }


        // 처음 익은 토마토가 1이므로 날짜 = maxVal - 1
        System.out.println(maxVal - 1);
    }
}
