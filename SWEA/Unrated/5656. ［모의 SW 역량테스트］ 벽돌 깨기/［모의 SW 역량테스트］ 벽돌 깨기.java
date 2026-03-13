import java.util.*;
import java.io.*;

public class Solution {
	static int n, w, h, minCnt;
	static int[][] map;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			// map 입력
			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			minCnt = Integer.MAX_VALUE;
			dfs(0);
			sb.append('#').append(t).append(' ').append(minCnt).append('\n');
		}
		System.out.println(sb);
	}

	// 중복 O, 순서 O
	private static void dfs(int cnt) {
		// 종료 조건
		if (cnt == n) {
			// 남은 벽돌 개수 갱신
			int count = countBrick();
			minCnt = Math.min(minCnt, count);
			return;
		}
		
		
		for (int col = 0; col < w; col++) {
			int[][] temp = new int[h][w];
			for (int i = 0; i < h; i++) {
				temp[i] = map[i].clone();
			}
			
			int row = findTopBrick(col);
			if (row != -1) {
				boom(row, col);
				down();
			}
			dfs(cnt+1);
			// map 원복
			for (int i = 0; i < h; i++) {
				map[i] = temp[i].clone();
			}
		}
	}

	private static int countBrick() {
		int count = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] > 0) {
					count++;
				}
			}
		}
		return count;
	}

	private static int findTopBrick(int col) {
		for (int row = 0; row < h; row++) {
			if (map[row][col] != 0) return row;
		}
		return -1;
	}

	private static void boom(int row, int col) {
		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {row, col, map[row][col]}); // row, col, 폭발 세기
		map[row][col] = 0;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int power = cur[2];
			
			for (int i = 0; i < 4; i++) {
				for (int j = 1; j < power; j++) {
					int nr = r + dr[i]*j;
					int nc = c + dc[i]*j;
					
					
					if (nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
					
					if (map[nr][nc] == 0) continue;
					
					if (map[nr][nc] > 1) {
						q.offer(new int[] {nr, nc, map[nr][nc]});
					} // 1인 벽돌은 해당 위치만 삭제하면 됨
					map[nr][nc] = 0;
				}
			}
		}
		
	}

	private static void down() {
		for (int col = 0; col < w; col++) {
			Deque<Integer> stack = new ArrayDeque<>();
			for (int row = 0; row < h; row++) {
				if (map[row][col] > 0) {
					stack.push(map[row][col]);
					map[row][col] = 0;
				}
			}
			int row = h-1;
			while (!stack.isEmpty()) {
				map[row--][col] = stack.pop();
			}
		}
	}
}
