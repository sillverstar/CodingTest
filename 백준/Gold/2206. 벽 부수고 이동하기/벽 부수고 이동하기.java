import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static boolean[][] map;
    static boolean[][][] visited; // broke 처리 포함
    
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new boolean[n][m];
        visited = new boolean[n][m][2]; // 0: 일반 방문 처리, 1: broke 사용 방문 처리
        
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = ((s.charAt(j) - '0') == 1)? true : false;
            }
        }
        

        
        System.out.println(bfs(0, 0));
    }


    private static int bfs(int sr, int sc) {
        // 큐 생성
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc, 1, 0});
        visited[sr][sc][0] = true; // 방문 처리
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            int state = cur[3];
            // state 0: 안 부숨, 1: 부숨
            
            // 종료 조건
            if (r == (n-1) && c == (m-1)) {
                return cnt;
            }
            
            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                // 범위 확인
                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                
                // 1인 경우, bomb이 0개라면 continue;
                if (map[nr][nc]) {
                	if (state == 0 && !visited[nr][nc][1]) {
                		 // 아직 안 부순 경우(visited[r][c][1] = false)에만 가능
                		 q.offer(new int[] {nr, nc, cnt+1, 1});
                		 visited[nr][nc][1] = true;
                	}
                	
                }
                
                // 0인 경우(갈 수 있는 경우)
                else {
                	if (!visited[nr][nc][state]) {
                		q.offer(new int[] {nr, nc, cnt+1, state}); // state는 그대로
                		visited[nr][nc][state] = true;
                	}
                    
                }    
            }
        }
        return -1;
    }
}
/*
5 6
010000
010000
010000
010111
000110
*/
