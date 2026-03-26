import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			int x = Integer.parseInt(st.nextToken());
			arr[i] = arr[i-1] + x;
		}

		int startIdx = 1;
		int endIdx = 1;
		
		int minLen = 100_000; // 10 <= N < 100_000 최대 길이로 초기화
		
		while (endIdx <= N) {
			int sum = arr[endIdx] - arr[startIdx-1];
			if (sum >= S) {
				int len = endIdx - (startIdx-1);
				minLen = Math.min(minLen, len);
				startIdx++;
			}
			else { // (sum < S)
				endIdx++;
			}
		}
		System.out.println(minLen == 100_000 ? 0 : minLen); // minLen이 갱신이 안 되는 경우 고려
		br.close();
	}
}