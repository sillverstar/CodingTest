import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		// 그래프 선언 + 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 진입 차수 배열 생성
		int[] indegree = new int[n+1]; // 1부터
		
		// 간선 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b); // a -> b
			indegree[b]++;
		}
		
		// 우선순위 큐 생성
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		// inDegree 0 넣기
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				pq.offer(i);
			}
		}
		
		// 위상정렬
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			sb.append(cur).append(' ');
			
			for (int next : graph.get(cur)) {
				indegree[next]--;
				
				if (indegree[next] == 0) {
					pq.offer(next);
				}
			}
		}
		
		System.out.println(sb);
	}

}
