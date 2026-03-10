import java.util.*;
import java.io.*;

public class Solution {
	static int N, minDist, dr, dc;
	static int[][] customers;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			dr = Integer.parseInt(st.nextToken());
			dc = Integer.parseInt(st.nextToken());
			
			customers = new int[N][2];
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				customers[i] = new int[] {x, y};
			}
			minDist = Integer.MAX_VALUE;
			visited = new boolean[N];
			dfs(sr, sc, 0, 0); // 현재 위치, 방문한 고객 수, 누적 거리
			
			sb.append('#').append(t).append(' ').append(minDist).append('\n');
		}
		System.out.println(sb);
	}


	private static void dfs(int r, int c, int cnt, int len) {
		// 종료 조건.
		if (cnt == N) {
			int nextLen = len + Math.abs(r - dr) + Math.abs(c - dc);
			minDist = Math.min(minDist, nextLen);
			return;
		}
		
		// 가지치기 추가: 현재 누적 거리가 지금까지 찾은 최솟값보다 크거나 같으면 다음을 탐색할 필요가 없음
		if (len >= minDist) return;
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			int nextLen = len + Math.abs(r - customers[i][0]) + Math.abs(c - customers[i][1]);
			visited[i] = true;
			dfs(customers[i][0], customers[i][1], cnt + 1, nextLen);
			visited[i] = false;
		}
	}
}
