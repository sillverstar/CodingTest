import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static int[][] map, dist;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		int t = 1;
		while (true) {
			n = Integer.parseInt(br.readLine());
			if (n == 0) break;
			
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dist = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			dijkstra();
			sb.append("Problem ").append(t).append(": ").append(dist[n-1][n-1]).append('\n');
			t++;
		}
		System.out.println(sb);
	}


	private static void dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		// 초기값. {r, c, cost}
		dist[0][0] = map[0][0];
		pq.offer(new int[] {0, 0, map[0][0]});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int cost = cur[2];
			
			if (cost > dist[r][c]) continue; // 추가
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				int nextCost = map[nr][nc] + cost;
				if (dist[nr][nc] > nextCost) {
					dist[nr][nc] = nextCost;
					pq.offer(new int[] {nr, nc, nextCost});
				}
			}
			
		}
	}

}
