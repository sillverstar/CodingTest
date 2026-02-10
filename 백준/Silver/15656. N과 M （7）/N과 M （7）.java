import java.util.*;
import java.io.*;

/*
 * 중복O 순서X
 */

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
		
//		System.out.println(Arrays.toString(arr));
		
		Arrays.sort(arr);
		nm7(0);
		System.out.println(sb);
	}
	
	private static void nm7(int cnt) {
		if (cnt == m) {
			for (int r : result) {
				sb.append(r).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < n; i++) {
			result[cnt] = arr[i];
			nm7(cnt + 1);
		}
	}

}
