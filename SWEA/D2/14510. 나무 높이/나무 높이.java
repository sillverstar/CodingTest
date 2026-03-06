import java.util.*;
import java.io.*;


public class Solution {
	static int n;
	static int[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			// 나무의 개수 n
			n = Integer.parseInt(br.readLine());
			
			// 입력 받으면서 최댓값 찾기
			int maxTree = 0;
			tree = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
				if (maxTree < tree[i]) {
					maxTree = tree[i];
				}
			}
			
			// 차이 계산
			int[] subTree = new int[n];
			for (int i = 0; i < n; i++) {
				subTree[i] = maxTree - tree[i];
			}
			
			
			// 짝수 홀수일 계산
			int even = 0;
			int odd = 0;
			
			for (int i = 0; i < n; i++) {
				even += subTree[i] / 2;
				odd += subTree[i] % 2;
			}
			
			
			// 짝수 -> 홀수 변환
			// 0202 -> 121
			// even 2 odd 0 -> even 1 odd 2로 변환 가능 [4일 -> 3일]
			while (even > (odd+1)) {
				even -= 1;
				odd += 2;
			}
			
			
			// 날짜 계산
			int day = 0;
			
			if (odd > even) {
				// 121210101
				day = 1 + ((odd-1)* 2);
			} else {
				// 121212 || 12121202
				day = even*2;
			}
			
			sb.append('#').append(t).append(' ').append(day).append('\n');
		}
		System.out.println(sb);
	}
}
