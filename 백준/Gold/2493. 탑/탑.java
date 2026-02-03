import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 입력 배열
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Deque<int[]> stack = new ArrayDeque<>(); // 값, 위치
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			while (true) {
				if (stack.isEmpty()) {
					sb.append(0).append(' ');
					stack.push(new int[] {arr[i], i + 1});
					break;
				}
				
				else { // 스택에 요소가 있는 경우
					if (stack.peek()[0] < arr[i]) {
						stack.pop();
					}
					else { // 스택.peek값이 현재 값보다 크거나 같은 경우
						sb.append(stack.peek()[1]).append(' ');
						stack.push(new int[] {arr[i], i + 1});
						break;
					}
				}
			}		
//			for (int[] s : stack) {
//				System.out.println(Arrays.toString(s));
//			}
//			System.out.println();
		}
		System.out.println(sb);
	}
}
