import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int k = Integer.parseInt(br.readLine());
		Deque<Integer> stack = new ArrayDeque<>();
		
		for (int i = 0; i < k; i++) {
			int temp = Integer.parseInt(br.readLine());
			if (temp != 0) {
				stack.push(temp);
			} else {
				stack.pop();
			}
		}
		
		int ans = 0;
		for (int s : stack) {
			ans += s;
		}

		System.out.println(ans);
	}

}
