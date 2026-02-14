import java.io.*;

public class Solution {
	static int maxTurn;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			maxTurn = 0;
			
			dfs(n, 0);
            sb.append('#').append(t).append(' ').append(maxTurn).append('\n');
        }
        System.out.print(sb);
	}
	
	
	private static void dfs(int num, int turn) { // num: 현재 숫자, turn: 지금까지 쪼갠 횟수
		if (num < 10) {
			maxTurn = Math.max(maxTurn, turn);
			return;
		}
		
		int[] numbers = intToArray(num);
		
		int limit  = (1 << numbers.length - 1);
		for (int mask = 1; mask < limit ; mask++) {
			int newNum = product(numbers, mask);
			dfs(newNum, turn + 1);
					
		}
	}

	private static int product(int[] numbers, int mask) {
		int newNum = 1;
		int cur = numbers[0];
		
		for (int i = 1; i < numbers.length; i++) {
			int idx = 1 << (i - 1); // 확인하려는 위치(bitmask의 시작인덱스(0)는 오른쪽, array 시작인덱스는 왼쪽)
			if ((mask & idx) != 0) {
				newNum *= cur;
				cur = numbers[i]; // cur <- 다음 요소
			}
			else {
				cur = cur * 10 + numbers[i]; // cur <- (int) cur + 다음 요소
			}
		}
		newNum *= cur; // 마지막
		return newNum;
	}
	
	
	// 자릿수별로 배열에 넣기 (char 배열 -> int 배열)
	private static int[] intToArray(int num) {
		char[] ch = String.valueOf(num).toCharArray();
		int[] result = new int[ch.length];
		
		for (int i = 0; i < ch.length; i++) {
			result[i] = ch[i] - '0';
		}
		return result;
	}	
}
