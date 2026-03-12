import java.util.*;
import java.io.*;

public class Main {
	static int r, c, totalCnt;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dr = {-1, 0, 1};
	static int[] dc = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		visited = new boolean[r][c];
		// map 입력
		for (int i = 0; i < r; i++) {
			String s = br.readLine();
			for (int j = 0; j < c; j++) {
				char temp = s.charAt(j);
				map[i][j] = (temp == '.')? 0:1;
			}
		}

		totalCnt = 0;
		for (int i = 0; i < r; i++) {
			if (!visited[i][0] && map[i][0] == 0) {
				visited[i][0] = true;
				if (dfs(i, 0)) {
					totalCnt++;
				}
			}
		}
		
		System.out.println(totalCnt);
	}

	private static boolean dfs(int x, int y) {
		if (y == c-1) {
			return true;
		}
		for (int i = 0; i < 3; i++) {
			int nr = x + dr[i];
			int nc = y + dc[i];
			
			if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
			
			if (visited[nr][nc]) continue;
			
			if (map[nr][nc] == 1) continue;
			
			visited[nr][nc] = true;
			// 종료 조건
			
			if (dfs(nr, nc)) {
				return true;
			}
			
				// 갈 수 있는 곳이 없을 때도 종료
		
		}
		
		return false;
		
		
	}
}
