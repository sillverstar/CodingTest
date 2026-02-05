import java.util.*;
import java.io.*;

public class Main {
	// 시작점 -> 최대 이동할 수 있는 곳(왼, 오) -> 각각의 왼오 ...
	// 이미 갔던 곳은 visited[i] <- true로 처리
	
	static int n, s;
	static int[] arr;
	static boolean[] visited;
	
	static int bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		
		// 초기값
		visited[start] = true;
		q.offer(start);
		
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			int jump = arr[current]; // 현재 위치의 점프 높이
			
			int left = current - jump;
			int right = current + jump;
			
			// 범위를 벗어나지 않고, 방문 false면 큐에 추가
			// 왼쪽 오른쪽 너비 같음
			// 왼쪽
			if ((left >= 1 && left <= n) && !visited[left]) {
				visited[left] = true;
				q.offer(left);
				cnt++;
			}
			// 오른쪽
			if ((right >= 1 && right <= n) && !visited[right]) {
				visited[right] = true;
				q.offer(right);
				cnt++;
			}
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n + 1];
		visited = new boolean[n + 1]; // 초기화 습관 들이기
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) { // 1부터 n까지 넣음
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		s = Integer.parseInt(br.readLine());
		
		int cnt = bfs(s);
		
		System.out.println(cnt);
		
	}

}
