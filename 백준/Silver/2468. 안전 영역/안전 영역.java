import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] arr;
	static boolean[][] visited;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void bfs(int sr, int sc, int height) {
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
				 
				 if (nr < 0 || nr >= n || nc < 0 || nc >= n) { // 배열 범위 확인
					 continue;
				 }
				 if (visited[nr][nc]) { // 방문 여부 확인
					 continue;
				 }
				 if (arr[nr][nc] <= height) { // 잠겨있으면 못 감
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
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
		int maxCnt = 1;
		
		for (int height = 0; height <= 100; height++) {
			visited = new boolean[n][n];
			
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j] && arr[i][j] > height) {
						bfs(i, j, height);
						cnt++;
					}
				}
			}
			if (cnt > maxCnt) maxCnt = cnt;
		}
		System.out.println(maxCnt);
	}
}