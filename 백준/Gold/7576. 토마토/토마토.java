import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken()); // 가로
        int n = Integer.parseInt(st.nextToken()); // 세로

        int[][] box = new int[n][m];

        // 상하좌우
        int[][] deltasP = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        Queue<int[]> queue = new LinkedList<>();

        // 입력 받으면서 익은 토마토(1) 위치를 전부 큐에 넣기 (멀티 소스)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                // 1: 익음, 0: 안 익음, -1: 비어있음
                box[i][j] = Integer.parseInt(st.nextToken());
                
                // 익어있는 토마토(==1; BFS 시작점)면 모두 큐에 넣음
                if (box[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        // BFS
        while (!queue.isEmpty()) { // queue가 내부가 빌 때까지
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + deltasP[d][0];
                int ny = y + deltasP[d][1];

                // 범위 체크
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 안 익은 토마토(0)만 익히기
                if (box[nx][ny] == 0) {
                    box[nx][ny] = box[x][y] + 1; // 날짜 기록
                    queue.add(new int[]{nx, ny});
                }
            }
        }
        
        // 최종
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (box[i][j] == 0) { // 안 익은 토마토 남아있으면 무조건 -1
                    System.out.println(-1);
                    return;
                }
                maxVal = Math.max(maxVal, box[i][j]);
            }
        }

        // 처음 익은 토마토가 1이므로 날짜 = maxVal - 1
        System.out.println(maxVal - 1);
    }
}
