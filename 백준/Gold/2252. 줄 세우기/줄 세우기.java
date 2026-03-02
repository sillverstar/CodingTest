import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		List<List<Integer>> graph = new ArrayList<>();
		
		// 초기화
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 진입 차수 배열
		int[] indegree = new int[n+1];
		
		// 간선 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			indegree[b]++;
		}
		
		// 큐 생성 + 진입 차수가 0인 값 넣기
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		
		// 위상 정렬
		while (!q.isEmpty()) {
			int cur = q.poll();
			System.out.print(cur + " ");
			
			for (int next : graph.get(cur)) {
				indegree[next]--;
				
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
	}
}
