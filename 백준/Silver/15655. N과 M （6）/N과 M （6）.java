import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] arr, result;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		nm6(0, 0);
		System.out.println(sb);
	}
	
	private static void nm6(int cnt, int start) {
		if (cnt == m) {
			// 끝났어요
			for (int r : result) {
				sb.append(r).append(' ');
			}
			sb.append('\n');
			return;
		}
		for (int i = start; i < n; i++) {
			result[cnt] = arr[i];
			nm6(cnt+1, i + 1);
		}
	}

}
