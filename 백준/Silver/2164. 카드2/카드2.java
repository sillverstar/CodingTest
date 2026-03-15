import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Deque<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		queue = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int top = queue.poll();
			if (!queue.isEmpty()) {
				queue.offer(queue.poll());
			} else {
				System.out.println(top);
				break;
			}
		}
	}
}