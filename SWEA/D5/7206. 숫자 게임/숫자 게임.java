import java.io.*;
import java.util.Arrays;

public class Solution {
	static int maxTurn;
	static int[] memo; // 각 숫자에 도달했을 때의 최댓값을 저장.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			maxTurn = 0;
			
			// 메모 추가
			memo = new int[100000]; // 입력 범위 1 ~ 99999
			Arrays.fill(memo, -1);
			
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
		
		// 이미 지금 num에 대해 지금보다 더 많거나 같은 turn으로 온 적이 있다면
		// 현재 탐색이 의미 X -> return;
		if (memo[num] >= turn) return; 
		memo[num] = turn;
		
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
