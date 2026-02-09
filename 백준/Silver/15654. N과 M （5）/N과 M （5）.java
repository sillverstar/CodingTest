import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr, result;
	static boolean[] visited;
	
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		visited = new boolean[n];
		
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		nm5(0);
		System.out.println(sb);
	}
	
	private static void nm5(int cnt) {
		if (cnt == m) {
			for (int r : result) {
				sb.append(r).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = 0; i < n; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			result[cnt] = arr[i];
			nm5(cnt + 1);
			visited[i] = false;
	
		}
	}
}
