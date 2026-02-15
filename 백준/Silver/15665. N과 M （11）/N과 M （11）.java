import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] input, result;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		input = new int[n];
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		dfs(0);
		System.out.println(sb);
	}
	
	private static void dfs(int cnt) {
		if (cnt == m) {
			for (int r : result) {
				sb.append(r).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (input[i] == result[cnt]) continue;
			result[cnt] = input[i];
			dfs(cnt+1);
		}
	}
	
	
}
