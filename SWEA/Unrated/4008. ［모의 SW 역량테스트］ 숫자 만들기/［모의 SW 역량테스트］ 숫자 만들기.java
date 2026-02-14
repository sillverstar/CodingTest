import java.util.*;
import java.io.*;

public class Solution {
	static int n, maxNum, minNum;
	static int[] operator, numbers;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			operator = new int[4];
			numbers = new int[n];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
			}
			
			maxNum = Integer.MIN_VALUE;
			minNum = Integer.MAX_VALUE;
			
			dfs(0, numbers[0]);
			
			System.out.println("#" + t + " " + (maxNum - minNum));
		}
	}
	private static void dfs(int cnt, int curNum) {
		if (cnt == (n-1)) {
			maxNum = Math.max(maxNum, curNum);
			minNum = Math.min(minNum, curNum);
			return;
		}
		
		for (int i = 0; i < 4; i++) { // 4번 반복
			if (operator[i] == 0) continue; // 연산자가 없으면 pass
			
			operator[i]--; // 연산자 선택
			
			// 계산
			int next = curNum;
			switch (i) {
			case 0:
				next += numbers[cnt+1];
				break;
			case 1:
				next -= numbers[cnt+1];
				break;
			case 2:
				next *= numbers[cnt+1];
				break;
			case 3:
				next /= numbers[cnt+1];
				break;
	
			}
			dfs(cnt + 1, next); // 재귀
			
			operator[i]++; // 연산자 원복
		}
	}
}
