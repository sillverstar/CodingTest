import java.util.*;
import java.io.*;

public class Solution {
	
	static class Info {
		int sum; // 전체 총합
		int maxCnt; // 최대 군집의 개수
		int dir; // 방향
		
		public Info(int sum, int maxCnt, int dir) {
			this.sum = sum;
			this.maxCnt = maxCnt;
			this.dir = dir;
		}
	}
	
	static int N, M, K;
	static List<int[]> microorganism;
	static Info[][] map;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 미생물 정보 입력
			microorganism = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				microorganism.add(new int[] {x, y, cnt, dir});
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}

			// 이동
			for (int i = 0; i < M; i++) {
				move();
			}
			
			int totalSum = 0;
			
			for (int[] cur : microorganism) {
				totalSum += cur[2];
			}
			sb.append('#').append(t).append(' ').append(totalSum).append('\n');
			
		}
		System.out.println(sb);
		
		
		
	}

	private static void move() {
		Info[][] checkMap = new Info[N][N];
		// checkMap 초기화
		
		for (int i = 0; i < microorganism.size(); i++) {
			int[] cur = microorganism.get(i);
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			int dir = cur[3];
			// 이동
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 범위 확인
			
			// 이동한 값이 약품에 닿는가?
			if (nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
				cnt /= 2;
				dir = (dir == 0) ? 1 : (dir == 1) ? 0 : (dir == 2) ? 3 : 2;
				if (cnt == 0) {
					continue;
				}
			}
			
			Info check = checkMap[nr][nc];
			// 처음 도착한 경우
			if (check == null) {
				checkMap[nr][nc] = new Info(cnt, cnt, dir);
			}
			
			else {
				check.sum += cnt;
				if (check.maxCnt < cnt) {
					check.maxCnt = cnt;
					check.dir = dir;
				}
			}
		}
		
		List<int[]> nextList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (checkMap[i][j] != null) {
					Info temp = checkMap[i][j];
					nextList.add(new int[] {i, j, temp.sum, temp.dir});
				}
				
			}
		}
		microorganism = nextList;
	}

}
