import java.io.*;
import java.util.*;

public class Solution {
	static int n, m;
	static int[][] map;
	static int maxHome;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxHome = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 1; k <= n + 1; k++) {
						int cost = k * k + (k-1) * (k-1);
						
						int homeCnt = countHome(i, j, k);
						
						if ((homeCnt * m) >= cost) {
							maxHome = Math.max(homeCnt, maxHome);
						}
						
					}
				}
			}
			
			
			sb.append('#').append(t).append(' ').append(maxHome).append('\n');
		}
		System.out.println(sb);
		
		
	}

	private static int countHome(int sr, int sc, int k) { // 중심 좌표
		int cnt = 0;
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				
				int dist = Math.abs(r - sr) + Math.abs(c - sc); // 맨해튼 거리
				
				if ((dist <= k - 1) && map[r][c] == 1) {
					cnt++;
				}
			}
		}
		return cnt;
	}	
}