import java.util.*;
import java.io.*;

public class Main {
	static int n, total;
	static long[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		input = new long[n];
		total  = 0;
		
		// 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			input[i] = Long.parseLong(st.nextToken());
			total += input[i];
		}
		
		long ans = 0;
		
		// 계산
		for (int i = 0; i < n; i++) {
			// total - 현재값
			total -= input[i];
			
			// 곱한 값 계산 (현재값 * 뒤쪽 total)
			ans += input[i] * total;
		}
		
		System.out.println(ans);
	}
	
}
