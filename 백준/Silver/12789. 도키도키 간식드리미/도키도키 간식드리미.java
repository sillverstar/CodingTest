import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Deque<Integer> stack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		stack = new ArrayDeque<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int snackNum = 1; // 형재 간식을 받을 수 있는 번호표
		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (now != snackNum) {
				stack.push(now);
			} else {
				snackNum++;
				// stack의 가장 윗 부분 확인
				while (!stack.isEmpty() && stack.peek() == snackNum) {
					stack.pop();
					snackNum++;
				}
			}
		}
		
		if (stack.isEmpty()) {
			System.out.println("Nice");
		} else {
			System.out.println("Sad");
		}
	}
}
