import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dp = new long[N+1]; // 공간복잡도: O(N)
		
		dp[1] = 1;
		if (N >= 2) dp[2] = 2;
		for (int i = 3; i <= N; i++) { // 시간복잡도: O(N)
			dp[i] = (dp[i-1] + dp[i-2]) % 10007;
		}
		System.out.println(dp[N]);
	}
}
