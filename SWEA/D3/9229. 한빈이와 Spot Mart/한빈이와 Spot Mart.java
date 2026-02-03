import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()); // 입력 과자 봉지의 개수
			int m = Integer.parseInt(st.nextToken()); // 무게 수 제한
			int[] snack = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			// System.out.println(Arrays.toString(snack));
			
			// 정렬
			Arrays.sort(snack);
			int s = 0, e = n-1, maxSum = -1;
			while (s < e) {
				int sum = snack[s] + snack[e];
				if (sum > m) {
					--e;
				} else {
					if (sum == m) {
                        maxSum = sum;
                        break;
					}
					else {
						++s;
					}
					maxSum = Math.max(maxSum, sum);
				}
			}
			sb.append('#').append(t+1).append(' ').append(maxSum).append('\n');
		}
		System.out.print(sb);
	}
}