import java.util.*;
import java.io.*;

public class Main {
	static int n, minCost;
	static int[][] map, costs;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int t = 1;
		StringBuilder sb = new StringBuilder();
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			
			minCost = Integer.MAX_VALUE;
			map = new int[n][n];
			costs = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					costs[i][j] = Integer.MAX_VALUE;
				}
			}
			
			
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			bfs();
			
			sb.append("Problem ").append(t).append(": ").append(costs[n-1][n-1]).append('\n');
			t++;
			
		}
		
		System.out.println(sb);
		
		

	}

	private static void bfs() {
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		// 가격이 저렴한 것부터 확인해야함. 그냥 큐로 하면 안됨
		
		int sr = 0, sc = 0;
		int sCost = map[sr][sc];
		q.offer(new int[] {sr, sc, sCost});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			
			if (cost > costs[r][c]) continue;
			
			// 끝까지 탐색
			if (r == n-1 && c == n-1) {
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				

				int tempCost = cost + map[nr][nc];
				if (tempCost < costs[nr][nc]) { // 저장된 금액보다 현재 경로의 비용이 더 싸면
					costs[nr][nc] = tempCost;
					q.offer(new int[] {nr, nc, tempCost} );
				}
				
			}
		}
	}
}
