import java.util.*;
import java.io.*;

public class Solution {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {

			// n
			int n = Integer.parseInt(br.readLine());
			
			// 입력 + 최댓값 찾기
			int maxTree = 0;
			int[] tree = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				if (maxTree < tree[i]) {
					maxTree = tree[i];
				}
			}
			
			// 가장 큰 나무와의 차이 게산
			for (int i = 0; i < n; i++) {
				tree[i] = maxTree - tree[i];
			}
			
			
			// 짝수 홀수 개수 게산
			int even = 0;
			int odd = 0;
			for (int i = 0; i < n; i++) {
				even += tree[i] / 2;
				odd += tree[i] % 2;
//				System.out.println(tree[i] + " : " + even + ", " + odd);
			}
			
			while (even > (odd+1)) {
				even -= 1;
				odd += 2;
			}
//			System.out.println(even + ", " + odd);
			
			// 정답 계산
			int ans = 0;
			if (odd > even) {
				ans = (odd - 1) * 2 + 1;
			} else {
				ans = even * 2;
			}
			sb.append('#').append(t).append(' ').append(ans).append('\n');
			
		}
		
		System.out.println(sb);
		
	}

}
