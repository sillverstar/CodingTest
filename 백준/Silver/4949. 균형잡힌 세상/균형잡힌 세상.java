import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = br.readLine();
			if (s.equals(".")) break;
			
			if (check(s)) {
				sb.append("yes").append('\n');
			}else {
				sb.append("no").append('\n');
			}
		}
		System.out.print(sb);
	}

	private static boolean check(String s) {
		Deque<Character> stack = new ArrayDeque<>();
		
		for (int i = 0; i < s.length(); i++) {
			char cur = s.charAt(i);
			if (cur == '[' || cur == '(') {
				stack.push(cur);
			} else if (cur == ']') {
				if (stack.isEmpty() || stack.pop() != '[') return false;
			} else if (cur == ')') {
				if (stack.isEmpty() || stack.pop() != '(') return false;
			}
		}
		return stack.isEmpty(); // 비어있으면 true 남아있으면 false
	}
}
