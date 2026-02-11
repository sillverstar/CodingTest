import java.util.*;
import java.io.*;

public class Solution {
	static int n, b, ans;
	static int[] arr; 
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			
			ans = Integer.MAX_VALUE;
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0);
			sb.append('#').append(t).append(' ').append(ans - b).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void dfs(int sum, int start) {
		if (sum >= b) {
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = start; i < n; i++) {
			dfs(sum + arr[i], i+1);
		}
	}
}