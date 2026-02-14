import java.io.*;
import java.util.*;

public class Solution {
	static int day, month, three, year;
	static int[] plan;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			day = Integer.parseInt(st.nextToken());
			month = Integer.parseInt(st.nextToken());
			three = Integer.parseInt(st.nextToken());
			year = Integer.parseInt(st.nextToken());
			
			plan = new int[13]; // 1월부터 사용
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = year;
			
			dfs(1, 0);
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
		
	}
	
	private static void dfs(int cnt, int cost) {
		// 가지치기: 1년 이용권보다 비싸지면 return;
		if (cost >= ans) return;
		
		// 종료 조건
		if (cnt > 12) {
			ans = Math.min(ans, cost);
			return;
		}
		
		if (plan[cnt] == 0) { // 값이 0이면 계산 안 하고 넘김
			dfs(cnt + 1, cost);
			return;
		}
		
		// 1일권
		dfs(cnt + 1, cost + plan[cnt] * day);
		
		// 1달권
		dfs(cnt + 1, cost + month);
		
		// 3달권
		dfs(cnt + 3, cost + three);
	}
}
