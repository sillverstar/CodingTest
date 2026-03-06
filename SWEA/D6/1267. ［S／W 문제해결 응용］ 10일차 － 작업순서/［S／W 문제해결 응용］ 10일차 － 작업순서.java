import java.util.*;
import java.io.*;

// 가중치가 모두 1, 선후관계가 O


public class Solution {
	static int v, e;
	static List<List<Integer>> graph;
	static int[] indegree;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		// 10개의 테스트 케이스
		sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			// graph 초기화
			graph = new ArrayList<>();
			for (int i = 0; i <= v; i++) {
				graph.add(new ArrayList<>());
			}
			
			indegree = new int[v+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < e; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				indegree[to]++;
			}
			
			sb.append('#').append(t).append(' ');
			topo();
		}
		System.out.println(sb);
	}
	private static void topo() {
		
		// 큐 생성
		Deque<Integer> q = new ArrayDeque<>();
		
		// indegree가 0인 경우 넣기
		for (int i = 1; i <= v; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			sb.append(cur).append(' ');
			
			for (int next : graph.get(cur)) {
				// indegree--
				indegree[next]--;
				
				// indegree가 0인 경우 넣기
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		sb.append('\n');
	}
}
