import java.util.*;
import java.io.*;

public class Solution {
	static final int  n = 100;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int tt = Integer.parseInt(br.readLine());
			ans = 0;
			map = new int[n][n];
			visited = new boolean[n][n];
			
			int sr = 0, sc = 0;
			
			for (int i = 0; i < n; i++) {
				String s = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = s.charAt(j) - '0';
					if (map[i][j] == 2) {
						sr = i;
						sc = j;
					}
				}
			}
			
			
			dfs(sr, sc);
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	private static void dfs(int r, int c) {
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			// 범위 확인
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			
			// 벽 확인
			if (map[nr][nc] == 1) continue;
			// 종료 조건
			if (map[nr][nc] == 3) {
				ans = 1;
				return;
			}
			
			if (!visited[nr][nc] && map[nr][nc] == 0) {
				visited[nr][nc] = true;
				dfs(nr, nc);
			}
		}
	}
}
