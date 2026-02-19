import java.io.*;
import java.util.*;

public class Main {
	static int N, M, H;
	static int[][][] tomato;
	static Queue<int[]> q;
	
	static int[] dz = {-1, 1, 0, 0, 0, 0};
	static int[] dx = {0, 0, -1, 1, 0, 0};
	static int[] dy = {0, 0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		tomato = new int[H][N][M];
		q = new ArrayDeque<>();
		
		// 1. 입력받으면서 익어있는 토마토 큐에 넣기
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					tomato[h][n][m] = Integer.parseInt(st.nextToken());
					
					if (tomato[h][n][m] == 1) {
						q.offer(new int[] {h, n, m});
						
					}
				}
			}
		}
		
		// 2. BFS 호출
		bfs();
		
		// 3. 정답 출력
		printAns();
		
	}

	private static void bfs() {
		while (!q.isEmpty()) {
		    int[] cur = q.poll();
		    int h = cur[0];
		    int r = cur[1];
		    int c = cur[2];

		    for (int i = 0; i < 6; i++) {
		        int nh = h + dz[i];
		        int nr = r + dx[i];
		        int nc = c + dy[i];

		        if (nh < 0 || nh >= H || nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

		        if (tomato[nh][nr][nc] == 0) {
		            tomato[nh][nr][nc] = tomato[h][r][c] + 1;
		            q.offer(new int[] {nh, nr, nc});
		        }
		    }
		}

		
	}
	
	private static void printAns() {
		int maxDay = 0;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					// 안 익은 토마토 확인
					if (tomato[h][n][m] == 0) {
						System.out.println(-1);
						return;
					}
					
					// 최댓값 계산
					maxDay = Math.max(maxDay, tomato[h][n][m]);
				}
				
			}
		}
		System.out.println(maxDay - 1);
	}

}
