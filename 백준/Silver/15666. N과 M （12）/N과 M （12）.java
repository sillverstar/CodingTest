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
		
		dfs(0, 0);
		System.out.println(sb);
	}
	
	private static void dfs(int cnt, int start) {
		if (cnt == m) {
			for (int r : result) {
				sb.append(r).append(' ');
			}
			sb.append('\n');
			return;
		}
		int prev = -1;
		for (int i = start; i < n; i++) {
			if (prev == input[i]) continue; // 이전에 저장된 값이랑 새로 저장하려는 값이 같으면 continue;
			
			result[cnt] = input[i];
			prev = input[i];
			dfs(cnt + 1, i);
		}
	}
}
