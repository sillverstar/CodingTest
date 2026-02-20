import java.util.*;
import java.io.*;

public class Main {
	static int n, rgCntF, rgCntT, bCnt;
	static int[][] map,visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		rgCntF = rgCntT = bCnt = 0;
		map = new int[n][n];
		visited = new int[n][n]; // 비트마스킹으로 관리 (RG: 3번째 비트, R:2번째 비트, G:1번째 비트, B:0번째 비트)
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				char temp = s.charAt(j);
				map[i][j] = (temp == 'R') ? 2 : (temp == 'G') ? 1 : 0;
			}
		}
		
		count();
	}
	
	
	private static void count() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((visited[i][j] & (1 << 0)) == 0 && map[i][j] == 0) {
					bfsB(i, j);
					bCnt++;
				}
				if ((visited[i][j] & (1 << 1)) == 0 && map[i][j] == 1) {
					bfsRG(i, j, map[i][j], true);
					rgCntF++;	
				}
				if ((visited[i][j] & (1 << 2)) == 0 && map[i][j] == 2) {
					bfsRG(i, j, map[i][j], true);
					rgCntF++;	
				}
				if ((visited[i][j] & (1 << 3)) == 0 && map[i][j] != 0) {
					bfsRG(i, j, 3, false);
					rgCntT++;	
				}
			
			}
		}
		int falseCnt = rgCntF + bCnt;
		int trueCnt = rgCntT + bCnt;
		System.out.println(falseCnt + " " + trueCnt);
	}
	
	
	private static void bfsB(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] |= (1 << 0);
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				if ( (visited[nr][nc] & (1 << 0)) == 0 && map[nr][nc] == 0) {
					q.offer(new int[] {nr, nc});
					visited[nr][nc] |= (1 << 0);
				}
			}
		}
	}

	
	private static void bfsRG(int sr, int sc, int value, boolean tf) {
		if (tf) {
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {sr, sc});
			visited[sr][sc] |= (1 << value);
			
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if ( (visited[nr][nc] & (1 << value)) == 0 && map[nr][nc] == value) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] |= (1 << value);
					}
				}
			}
		}
		else {
			Queue<int[]> q = new ArrayDeque<>();
			q.offer(new int[] {sr, sc});
			visited[sr][sc] |= (1 << value);
			
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int r = cur[0];
				int c = cur[1];
				
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if ( (visited[nr][nc] & (1 << value)) == 0 && map[nr][nc] > 0) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] |= (1 << value);
					}
				}
			}
		}
	}
}
