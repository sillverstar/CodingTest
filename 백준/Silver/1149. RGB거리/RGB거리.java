import java.util.*;
import java.io.*;

public class Main {
	static final int RGB = 3;
	static int N;
	static int[][] cost, dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		
		cost = new int[N+1][RGB];
		dp = new int[N+1][RGB];
		
		// cost 입력
		for (int i = 1; i <= N; i++) { // 1번 집부터 ~ N번 집까지
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < RGB; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// dp 채우기
		filldp();

		int minCost = dp[N][0];
		for (int i = 1; i < RGB; i++) {
			minCost = Math.min(minCost, dp[N][i]);
		}
		
		System.out.println(minCost);
	}

	private static void filldp() {
		for (int i = 1; i <= N; i++) {
			for (int color = 0; color < RGB; color++) {
				dp[i][color] = Math.min(dp[i-1][(color+1) % 3], dp[i-1][(color+2) % 3]) + cost[i][color];
			}
		}
	}
}
