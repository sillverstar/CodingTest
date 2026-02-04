import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[][] tomato;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void bfs(Queue<int[]> queue) { // 입력을 큐로
		while (!queue.isEmpty()) {
			int[] current = queue.poll();
			int r = current[0];
			int c = current[1];
			
			for (int k = 0; k < 4; k++) {
				int nr = r + dr[k];
				int nc = c + dc[k];
				 
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) { // 배열 범위 확인
					continue;
				}
				if (tomato[nr][nc] != 0) {
					continue;
				}
			
				//토마토 배열을 날짜 기록용으로 사용
				tomato[nr][nc] = tomato[r][c] + 1;
	            queue.offer(new int[] {nr, nc});
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		tomato = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				tomato[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 여기서 큐 생성 + 초기 시작점 넣기
		Queue<int[]> queue = new ArrayDeque<>();

		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < m; j++) {
		        if (tomato[i][j] == 1) {
		            queue.offer(new int[] {i, j});
		        }
		    }
		}
		
		bfs(queue);
		
		int ans = 1;
		for (int i = 0; i < n; i++) {
		    for (int j = 0; j < m; j++) {
		        if (tomato[i][j] == 0) {   // 안 익은 토마토 남음
		            System.out.println(-1);
		            return;
		        }
		        if (tomato[i][j] > ans) ans = tomato[i][j];
		    }
		}
		System.out.println(ans - 1);
	}

}
