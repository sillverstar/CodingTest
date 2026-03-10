import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
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
		Deque<Character> stack =new ArrayDeque<>();
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
		
		return stack.isEmpty();
		
	}
		
}
