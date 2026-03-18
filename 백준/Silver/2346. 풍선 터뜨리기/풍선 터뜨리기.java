import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Deque<int[]> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			int x = Integer.parseInt(st.nextToken());
			queue.offer(new int[] {i, x});
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int[] cur = queue.poll();
			int idx = cur[0];
			int value = cur[1];
			sb.append(idx).append(' ');
			if (!queue.isEmpty()) {
				if (value > 0) {
					for (int j = 0; j < Math.abs(value)-1; j++) {
						queue.offerLast(queue.pollFirst());
					}
				}
				else {
					for (int j = 0; j < Math.abs(value); j++) {
						queue.offerFirst(queue.pollLast());
					}
				}
			}
		}
		System.out.println(sb);
	}
}


