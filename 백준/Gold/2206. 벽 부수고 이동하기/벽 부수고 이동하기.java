import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static boolean[][] map;
	static int[][][] dist;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < m; j++) {
				int temp = s.charAt(j) - '0';
				map[i][j] = (temp == 1);
			}
		}
		
		
		dist = new int[n][m][2];
		
		bfs(0, 0);

		int ans = Math.max(dist[n-1][m-1][0], dist[n-1][m-1][1]);
		System.out.println((ans == 0)? -1 : ans);
		
		
	}
	private static void bfs(int sr, int sc) {
		// 큐 생성 + 초기값 넣기
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc, 1});
		dist[sr][sc][1] = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int use = cur[2];
			
			if (r == (n-1) && c == (m-1)) {
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위 확인
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
			
				// 갈 수 없는 경우 1: 이미 방문한 경우
				if (dist[nr][nc][use] > 0) continue;
				
				// 갈 수 없는 경우 2: 1(벽)인데 use가 0인 경우
				if ((map[nr][nc]) && (use == 0)) continue;
			
				
				// 갈 수 있는 경우 1: 1(벽)인데 use가 0보다 큰 경우 [use 사용 O]
				if ((map[nr][nc]) && (use > 0)) { // && (use > 0) 작성 안해도 되지만 가독성을 위해 명시적으로 작성
					q.offer(new int[] {nr, nc, use-1});
					dist[nr][nc][use-1] = dist[r][c][use] + 1;
				}
				
				// 갈 수 있는 경우 2: 0인 경우 [use 사용 X]
				if ((!map[nr][nc])) {
					q.offer(new int[] {nr, nc, use});
					dist[nr][nc][use] = dist[r][c][use] + 1;
					
				}
			}
		}
	}
}