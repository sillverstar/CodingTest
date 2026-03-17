import java.util.*;
import java.io.*;

public class Main {
	static int n, k;
	static Deque<Integer> queue;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		queue = new ArrayDeque<>();
		
		for (int i = 1; i <= n; i++) {
			queue.offer(i);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		for (int i = 0; i < n-1; i++) { // n-1번
			for (int j = 0; j < k-1; j++) { // k-1번 
				queue.offer(queue.poll());
			}
			sb.append(queue.poll()).append(',').append(' ');
		}
		sb.append(queue.poll()).append('>'); // 1번
		System.out.println(sb);
	}
}
