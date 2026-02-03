import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine()); // 용액 개수
		
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		Arrays.sort(arr);
		//System.out.println(Arrays.toString(arr));
		int s = 0, e = n-1;
		int minSum = Integer.MAX_VALUE;
		int[] ans = new int[] {-1, -1};
		
		while (s < e) {
			int sum = arr[s] + arr[e];
			// 절댓값 비교
			if (Math.abs(sum) < minSum) {
				minSum = Math.abs(sum);
				ans[0] = s;
				ans[1] = e;
			}
			
			// s, e 업데이트
			if (sum > 0) {
				--e;
			} else { // 음수
				++s;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(arr[ans[0]]).append(' ').append(arr[ans[1]]);
		System.out.println(sb);
	}
}
