import java.io.*;
import java.util.*;

public class Solution {
	static int n, k, maxCnt;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> peek;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			visited = new boolean[n][n];
			peek = new ArrayDeque<>();
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxCnt = 0;
			searchPeek();
			bfs();
			sb.append('#').append(t).append(' ').append(maxCnt).append('\n');
		}
		System.out.println(sb);
	}

	
	private static void bfs() {
		// 큐에 들어간 값들을 전부 dfs로 확인.
		while (!peek.isEmpty()) {
			int[] cur = peek.poll();
			int r = cur[0];
			int c = cur[1];
			visited[r][c] = true;
			dfs(1, r, c, false);
			visited[r][c] = false;
		}
	}

	private static void dfs(int cnt, int r, int c, boolean useK) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			if (visited[nr][nc]) continue;
			
			if (map[r][c] > map[nr][nc]) {
				visited[nr][nc] = true;
				dfs(cnt + 1, nr, nc, useK);
				visited[nr][nc] = false;
			}
			else if (!useK) {
				int original = map[nr][nc];
				int target = map[r][c] - 1; // 목표 높이. 한 칸만 낮으면 갈 수 있음
				int need = original - target; 
				
				if (need <= k) { // 깎아야 하는 양이 k보다 적을 때만 이동
					map[nr][nc] = target; // target으로 변경
					visited[nr][nc] = true;
					dfs(cnt + 1, nr, nc, !useK);
					visited[nr][nc] = false;
					map[nr][nc] = original; // 원래값으로 복구
					
				}
			}
		}
		maxCnt = Math.max(cnt, maxCnt);
	}

	private static void searchPeek() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] > max) { // 기존 max값보다 더 큰 값 발견
					max = map[i][j]; // 최댓값 갱신
					peek.clear(); // 큐 비우기
					peek.offer(new int[] {i, j}); // 큐에 삽입
				}
				else if (map[i][j] == max) { // max 값이랑 같은 경우에도 큐에 삽입
					peek.offer(new int[] {i, j});
				}
			}
		}
	}
}
