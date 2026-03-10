import java.util.*;
import java.io.*;

public class Main {
	static Deque<Character> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			String s = br.readLine();
			if (check(s)) {
				sb.append("YES").append('\n');
			} else {
				sb.append("NO").append('\n');
			}

			
		}
		System.out.print(sb);
		
	}

	private static boolean check(String s) {
		stack.clear();
		for (int i = 0; i < s.length(); i++) {
			char tmp = s.charAt(i);
			if (tmp == ')') {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					return false;
				}
			} else {
				stack.push(tmp);
			}
		}
		
		if (!stack.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
		
}
