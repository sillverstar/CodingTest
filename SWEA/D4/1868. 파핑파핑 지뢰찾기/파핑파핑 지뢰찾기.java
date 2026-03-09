import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, -1, 1, 1, -1, 1, 0, 0};
	static int[] dc = {-1, 1, -1, 1, 0, 0, -1, 1};
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			map = new int[n][n];
			visited = new boolean[n][n];
			
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					char temp = s.charAt(j);
					map[i][j] = (temp == '.')? 0 : -1;
					
				}
			}
			
			for (int i = 0;i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] != -1) {
						countMap(i, j);
					}
				}
			}
			
			int count = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if ((map[i][j] == 0) && (!visited[i][j])) {
						click(i, j);
						count++;
					}
				}
			}

			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if ((map[i][j] > 0) && (!visited[i][j])) {
						count++;
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}


	private static void click(int sr, int sc) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			
			if (map[r][c] == 0) {
				for (int i = 0; i < 8; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					
					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if (map[nr][nc] == -1) continue;
					
					if (map[nr][nc] >= 0 && !visited[nr][nc]) {
						q.offer(new int[] {nr, nc});
						visited[nr][nc] = true;
					}
				}
			}
			
		}
	}


	private static void countMap(int r, int c) {
		for (int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			
			if (map[nr][nc] == -1) {
				map[r][c]++;
			}
		}
	}
}
