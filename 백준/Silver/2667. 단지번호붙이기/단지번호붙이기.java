import java.util.*;
import java.io.*;

public class Main {
	static int n, cnt;
	static boolean[][] map, visited;
	static List<Integer> homeCnt;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		cnt = 0;
		
		map = new boolean[n][n];
		visited = new boolean[n][n];
		
		homeCnt = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = ((s.charAt(j) - '0') == 1);
			}
		}

		

		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((!visited[i][j]) && (map[i][j])) {
					//bfs(i, j);
					dfs(i, j);
					cnt++;
				}
			}
		}
		

		Collections.sort(homeCnt);

		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append('\n');
		for (int h : homeCnt) {
			sb.append(h).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void dfs(int sr, int sc) {
		int home = 0;
		// 큐 생성
		Deque<int[]> stack = new ArrayDeque<>();
		stack.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while (!stack.isEmpty()) {
			int[] cur = stack.pop();
			int r = cur[0];
			int c = cur[1];
			home++;
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				// 범위 확인
				if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
				
				// 집이 없는 경우 확인
				if (!map[nr][nc]) continue;
				
				// 이미 방문한 경우 확인
				if (visited[nr][nc]) continue;
				
				stack.offer(new int[] {nr, nc});
				visited[nr][nc] = true;
			}
		}
		
		// homeCnt 추가
		homeCnt.add(home);
		
	}

}
