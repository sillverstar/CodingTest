import java.util.*;
import java.io.*;

public class Main {
	static int n;
	static Deque<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		queue = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String c = st.nextToken();
			
			if (c.equals("push")) {
				int x = Integer.parseInt(st.nextToken());
				queue.offer(x);
			}
			else if (c.equals("pop")) {
				int x = -1;
				if (!queue.isEmpty()) {
					x = queue.poll();
				}
				sb.append(x).append('\n');
			}
			else if (c.equals("size")) {
				sb.append(queue.size()).append('\n');
			}
			else if (c.equals("empty")) {
				sb.append((queue.isEmpty()) ? 1 : 0).append('\n');
			}
			else if (c.equals("front")) {
				int x = -1;
				if (!queue.isEmpty()) {
					x = queue.peekFirst();
				}
				sb.append(x).append('\n');
			} else if (c.equals("back")) {
				int x = -1;
				if (!queue.isEmpty()) {
					x = queue.peekLast();
				}
				sb.append(x).append('\n');
			}
		}
		System.out.println(sb);
	}
}
