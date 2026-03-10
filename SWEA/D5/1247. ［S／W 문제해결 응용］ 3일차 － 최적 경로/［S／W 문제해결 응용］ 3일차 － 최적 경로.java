import java.util.*;
import java.io.*;

public class Solution {
	static int N, totalLen, dr, dc;
	static int[][] cust;
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
			
			cust = new int[N][2];
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				cust[i] = new int[] {x, y};
			}
			totalLen = Integer.MAX_VALUE;
			visited = new boolean[N];
			dfs(sr, sc, 0, 0); // 좌표, cnt, len
			
			sb.append('#').append(t).append(' ').append(totalLen).append('\n');
		}
		System.out.println(sb);
	}


	private static void dfs(int r, int c, int cnt, int len) {
		// 종료 조건.
		if (cnt == N) {
			int nextLen = len + Math.abs(r - dr) + Math.abs(c - dc);
			totalLen = Math.min(totalLen, nextLen);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			int nextLen = len + Math.abs(r - cust[i][0]) + Math.abs(c - cust[i][1]);
			visited[i] = true;
			dfs(cust[i][0], cust[i][1], cnt + 1, nextLen);
			visited[i] = false;
		}
	}
}
