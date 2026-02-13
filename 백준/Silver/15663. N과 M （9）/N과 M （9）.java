import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] input, result;
	static boolean[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 입력
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		input = new int[n];
		result = new int[m];
		selected = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		// 정렬 1 7 9 9
		Arrays.sort(input);
		
		nm9(0);
	}
	
	
	private static void nm9(int cnt) {
		if (cnt == m) {
			for (int r : result) {
				System.out.print(r + " ");
			}
			System.out.println();
			return;
		}
		
		int prev = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			if (selected[i]) continue;
			if (input[i] == prev) continue;
			
			selected[i] = true;
			result[cnt] = input[i];
			prev = input[i];
			
			nm9(cnt+1);
			
			selected[i] = false;	
		}	
	}
}
