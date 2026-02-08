import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static char[][] map;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
//		for (int i = 0; i < n; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		
		int minCnt = mazeRun();
		
		System.out.println(minCnt);
	}


	private static int mazeRun() {
		// 시작점(0)을 찾아 bfs 호출
		for (int sr = 0; sr < n; sr++) {
			for (int sc = 0; sc < m; sc++) {
				if (map[sr][sc] == '0') {
					return bfs(sr, sc);
				}
			}
		}
		return -1;
	}


	private static int bfs(int sr, int sc) {
		// 방문 여부 관리
		boolean[][][] visited = new boolean[n][m][64]; // 키를 비트마스크로 관리: 6개의 키 관리 -> 6개의 비트 사용 -> 2^6 = 64개 관리 필요
		Queue<int[]> q = new ArrayDeque<>(); // r, c, dist(이동 횟수), keys(열쇠 보유 상태)
		
		// 시작 위치 q에 넣기 + 방문 표시
		q.offer(new int[] {sr, sc, 0, 0});
		visited[sr][sc][0] = true;
		
		
		// 내 위치 기준 4방향 확인
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0];
			int c = current[1];
			int dist = current[2];
			int keys = current[3];
			
			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
				
				char ch = map[nr][nc];
				int newKeys = keys; // 000000
				
				// 벽
				if (ch == '#') continue;
				
				// 열쇠
				if (ch >= 97 && ch <= 102) {
					newKeys = keys | (1 << (ch - 'a')); // 추가 'a' 부터 1, 2, 3, 4, 5, 6 idx
				}
				
				// 문
				if (ch >= 65 && ch <= 70) {
					int checkKeys = keys & (1 << (ch - 'A')); // 확인 idx
					if (checkKeys == 0) {
						continue;
					}
				}	
				
				// 출구
				if (ch == '1') {
					return dist + 1;
				}
				
				// 중복된 상태는 큐에 넣지 않아야 무한 반복을 막을 수 있음
				// ex) . a 에서 (0, 1) -> (0, 0) -> (0, 1) 가는 상황[== 의미가 없음]
				if (!visited[nr][nc][newKeys]) {
					visited[nr][nc][newKeys] = true;
					q.offer(new int[] {nr, nc, dist + 1, newKeys});
				}
			}
		}
		return -1;
	}
}