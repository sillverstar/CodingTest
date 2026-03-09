import java.util.*;
import java.io.*;

public class Main {
	static Deque<Integer> stack;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		
		stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			
			if (command == 1) {
				int x = Integer.parseInt(st.nextToken());
				stack.push(x);
			}
			else if (command == 2) {
				if (!stack.isEmpty()) {
					int pop = stack.pop();
					sb.append(pop).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
			}
			else if (command == 3) {
				sb.append(stack.size()).append('\n');
			}
			else if (command == 4) {
				if (stack.isEmpty()) {
					sb.append(1).append('\n');
				} else {
					sb.append(0).append('\n');
				}
			}
			else if (command == 5) {
				if (!stack.isEmpty()) {
					sb.append(stack.peek()).append('\n');
				} else {
					sb.append(-1).append('\n');
				}
			}
		}
		
		System.out.print(sb);
		
	}

}
