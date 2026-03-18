import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static public Deque<Integer> deque;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		deque = new ArrayDeque<>();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int command = Integer.parseInt(st.nextToken());
			
			if (command == 1) {
				int x = Integer.parseInt(st.nextToken());
				deque.offerFirst(x);
			}
			else if (command == 2) {
				int x = Integer.parseInt(st.nextToken());
				deque.offerLast(x);
			}
			else if (command == 3) {
				sb.append(!(deque.isEmpty())?(int)deque.pollFirst() : -1).append('\n');
			}
			else if (command == 4) {
				sb.append(!(deque.isEmpty())?(int)deque.pollLast() : -1).append('\n');
			}
			else if (command == 5) {
				sb.append(deque.size()).append('\n');
			}
			else if (command == 6) {
				sb.append(!(deque.isEmpty())? 0 : 1).append('\n');
			}
			else if (command == 7) {
				sb.append(!(deque.isEmpty())?(int)deque.peekFirst() : -1).append('\n');
			}
			else if (command == 8) {
				sb.append(!(deque.isEmpty())?(int)deque.peekLast() : -1).append('\n');
			}
		}
		System.out.print(sb);
	}

}
