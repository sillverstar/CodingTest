import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[][] watered;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
    
	public static void water(int height) {
		watered = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] <= height) {
					watered[i][j] = true;
				}
			}
		}
	}
	
	public static void bfs(int sr, int sc) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		
		queue.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			 int r = current[0];
			 int c = current[1];
			 
			 for (int k = 0; k < 4; k++) {
				 int nr = r + dr[k];
				 int nc = c + dc[k];
				 
				 if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
					 continue;
				 }
				 if (watered[nr][nc] || visited[nr][nc]) {
					 continue;
				 }
				 visited[nr][nc] = true;
				 queue.offer(new int[] {nr, nc});
			 }
		 }
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		int maxRange = -1, minRange = -1;
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (i == 0  && j == 0) {
					maxRange = minRange = arr[i][j];
				}
				else {
					if (arr[i][j] > maxRange) {
						maxRange = arr[i][j];
					}
					else if (arr[i][j] < minRange){
						minRange = arr[i][j];
					}
				}
			}
		}
		int maxCnt = 1;
		
		for (int height = minRange - 1; height < maxRange; height++) {
			visited = new boolean[n][n];

			water(height);
			
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!watered[i][j] && !visited[i][j]) {
						bfs(i, j);
						cnt++;
					}
				}
			}
			if (cnt > maxCnt) maxCnt = cnt;
		}
		System.out.println(maxCnt);
	}
}