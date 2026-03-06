import java.util.*;
import java.io.*;

// dijkstra(bfs)
public class Solution {
	static int n, minCost;
	static int[][] map;
	static int[][] dp;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			minCost = 0;
			
			map = new int[n][n];
			dp = new int[n][n];

			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			
			bfs();

			minCost = dp[n-1][n-1];
			sb.append('#').append(t).append(' ').append(minCost).append('\n');
		}
		System.out.println(sb);
	}

	
	private static void bfs() {
		// dp 초기화
		for (int i = 0; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		Deque<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {0, 0, 0});
		dp[0][0] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			
			if (dp[r][c] < cost) continue;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				int nCost = cost + map[nr][nc];
				// 기존 값 vs 새로 계산한 값
				if (nCost < dp[nr][nc]) {
					q.offer(new int[] {nr, nc, nCost});
					dp[nr][nc] = nCost;
				}
			}
		}
	}
}
