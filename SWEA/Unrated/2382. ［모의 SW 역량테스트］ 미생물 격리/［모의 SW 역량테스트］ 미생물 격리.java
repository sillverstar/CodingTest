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
	static List<int[]> microbes;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[] reverseDir = {1, 0, 3, 2}; // 방향 반전 배열을 만듦
	
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
			microbes = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				microbes.add(new int[] {x, y, cnt, dir}); // x, y, 미생물 수, 방향
			}

			// M번 이동
			for (int i = 0; i < M; i++) {
				move();
			}

			
			int totalSum = 0;
			for (int[] cur : microbes) {
				totalSum += cur[2];
			}

			sb.append('#').append(t).append(' ').append(totalSum).append('\n');
		}
		System.out.println(sb);
	}

	private static void move() {
		Info[][] checkMap = new Info[N][N];
		
		for (int i = 0; i < microbes.size(); i++) {
			int[] cur = microbes.get(i);
			int r = cur[0];
			int c = cur[1];
			int cnt = cur[2];
			int dir = cur[3];
			
			// 이동
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			
			// 이동한 값이 약품에 닿는가?
			if (nr == 0 || nr == N-1 || nc == 0 || nc == N-1) {
				cnt /= 2;
				dir = reverseDir[dir]; // 방향 반전
				if (cnt == 0) {
					continue;
				}
			}
			
			Info cell = checkMap[nr][nc];
			// 처음 도착한 경우
			if (cell == null) {
				checkMap[nr][nc] = new Info(cnt, cnt, dir);
			}
			// 이미 도착한 미생물 군집이 있는 경우
			else {
				cell.sum += cnt;
				if (cell.maxCnt < cnt) {
					cell.maxCnt = cnt;
					cell.dir = dir;
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
		microbes = nextList;
	}
}
