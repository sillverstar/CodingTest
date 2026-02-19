import java.util.*;
import java.io.*;


public class Solution {
	static int n, maxCnt;
	static int[][] cheese;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			maxCnt = 0;
			cheese = new int[n][n]; // 초기화: 0
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cheese[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			
			for (int x = 0; x <= 100; x++) {
				visited = new boolean[n][n]; // 초기화: false
				checkCnt(x);
			}
			sb.append('#').append(t).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}

	private static void checkCnt(int x) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// 방문한 적 없고, X보다 클 때
				if (!visited[i][j] && cheese[i][j] > x) {
					//bfs(x, i, j);
					dfs(x, i, j);
					cnt++;
				}
			}
		}
		maxCnt = Math.max(maxCnt, cnt);
	}
	
	private static void dfs(int x, int sr, int sc) {
		int r = sr;
		int c = sc;
		visited[sr][sc] = true;
		
		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 범위 확인
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			// 확장 확인
			if (visited[nr][nc] || cheese[nr][nc] <= x) continue;
			visited[nr][nc] = true;
			dfs(x, nr, nc);
		}
	}
	
	
	private static void bfs(int x, int sr, int sc) {
		// 큐 생성
		Queue<int[]> q = new ArrayDeque<>();
		
		// 초기값 큐에 넣기
		q.offer(new int[] {sr, sc});
		// 방문 처리
		visited[sr][sc] = true;
		
		
		// 큐가 빌 때까지
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			// 4방 탐색
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위 확인
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				// 확장 확인
				if (visited[nr][nc] || cheese[nr][nc] <= x) continue;
				
				q.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
	}
}
