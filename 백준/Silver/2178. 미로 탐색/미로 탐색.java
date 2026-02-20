import java.util.*;
import java.io.*;

public class Main {
	static int n, m, minCnt;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		 bfs();
		 System.out.println(minCnt);
	}

	private static void bfs() {
		// 큐 생성 및 초기값 큐에 넣기
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, 1}); // sr, sc, sCnt
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			// poll
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			
			// 탐색 종료 조건
			if (r == n-1 && c == m-1) {
				minCnt = cnt;
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위 확인
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				// 방문 여부 + 벽 확인
				if (visited[nr][nc] || map[nr][nc] == 0) continue;
				
				q.offer(new int[] {nr, nc, cnt + 1});
				visited[nr][nc] = true;
			}
		}
	}
}
