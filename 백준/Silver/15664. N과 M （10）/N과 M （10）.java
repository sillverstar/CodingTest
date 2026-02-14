import java.util.*;
import java.io.*;

// 조합(중복X 순서X)
// 같은 자리에는 같은 값이 들어올 수 없음

public class Main {
	static int n, m;
	static int[] input, result;
	
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
	}
	
	
	private static void dfs(int cnt, int start) {
		if (cnt == m) {
			for (int r : result) {
				System.out.print(r + " ");
			}
			System.out.println();
			return;
		}
		
		int prev = -1;
		for (int i = start; i < n; i++) {
			if (input[i] == prev) continue;
			result[cnt] = input[i];
			prev = result[cnt];
			dfs(cnt + 1, i + 1);		
		}
	}
}
