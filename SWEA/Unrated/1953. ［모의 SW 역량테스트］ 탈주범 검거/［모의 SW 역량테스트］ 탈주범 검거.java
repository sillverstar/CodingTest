import java.util.*;
import java.io.*;

public class Solution {
	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int totalCnt;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			totalCnt = 0;
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
//			
			bfs();
			
			sb.append('#').append(t).append(' ').append(totalCnt).append('\n');
		}
		System.out.println(sb);
	}

	private static void bfs() {
		// 큐
		Queue<int[]> q = new ArrayDeque<>();
		
		// 첫 번째
		totalCnt++;
		q.offer(new int[] {R, C, L - 1});
		visited[R][C] = true;
		
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];
			
			if (t == 0) {
				continue;
			}
			
			int curMap = map[r][c];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				if (visited[nr][nc]) continue;
				
	            if (i == 0 && !(curMap == 1 || curMap == 2 || curMap == 4 || curMap == 7)) continue; // 상
	            if (i == 1 && !(curMap == 1 || curMap == 2 || curMap == 5 || curMap == 6)) continue; // 하
	            if (i == 2 && !(curMap == 1 || curMap == 3 || curMap == 6 || curMap == 7)) continue; // 좌
	            if (i == 3 && !(curMap == 1 || curMap == 3 || curMap == 4 || curMap == 5)) continue; // 우
	            
	            int nextMap = map[nr][nc];
				
				switch (i) {
				case 0:
					if (nextMap == 1 || nextMap == 2 || nextMap == 5 || nextMap == 6) {
						q.offer(new int[] {nr, nc, t - 1});
						visited[nr][nc] = true;
						totalCnt++;
					}
					break;
				case 1:
					if (nextMap == 1 || nextMap == 2 || nextMap == 4 || nextMap == 7) {
						q.offer(new int[] {nr, nc, t  -1});
						visited[nr][nc] = true;
						totalCnt++;
					}
					
					break;
				case 2:
					if (nextMap == 1 || nextMap == 3 || nextMap == 4 || nextMap == 5) {
						q.offer(new int[] {nr, nc, t - 1});
						visited[nr][nc] = true;
						totalCnt++;
					}
					
					break;
				case 3:
					if (nextMap == 1 || nextMap == 3 || nextMap == 6 || nextMap == 7) {
						q.offer(new int[] {nr, nc, t - 1});
						visited[nr][nc] = true;
						totalCnt++;
					}
					
					break;
				}
				
			}
		}
		
		
		
	}

}
