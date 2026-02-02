import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()); // 배열 개수
		int m = Integer.parseInt(st.nextToken()); // 입력 개수

		// 배열 입력
		int[] arr = new int[n + 1]; // arr[0]은 누적합 계산용
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 누적합 배열
		int[] ps = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			ps[i] = arr[i] + ps[i - 1];
		}
		
		int[] result = new int[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			result[i] = ps[end] - ps[start - 1];
			
		}
		
		StringBuilder sb  = new StringBuilder();
		for (int r : result) {
			sb.append(r).append('\n');
		}
		System.out.print(sb);
		
		br.close();
	}

}
