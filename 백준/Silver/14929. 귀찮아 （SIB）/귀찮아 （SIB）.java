import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static long[] input, sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		input = new long[n+1];
		sum = new long[n+1];
		
		// 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			input[i] = Long.parseLong(st.nextToken());
		}
		
		// 계산
		if (n >= 2) {
			for (int i = 2; i <= n; i++) {
				sum[i] = sum[i-1] + CalculateSum(i);
			}
		}
		System.out.println(sum[n]);
		
	}
	

	private static long CalculateSum(int idx) {
		long sum = 0;
		for (int i = 1; i < idx; i++) {
			sum += input[i] * input[idx];
		}
		return sum;
	}
}
