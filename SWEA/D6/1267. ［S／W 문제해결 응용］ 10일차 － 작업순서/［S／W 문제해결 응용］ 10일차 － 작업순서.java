import java.util.*;
import java.io.*;

public class Solution {
	static int v, e;
	static List<List<Integer>> graph;
	static int[] inDegree, path;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = 10;
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken()); // 정점 수
			e = Integer.parseInt(st.nextToken()); // 간선 수
			graph = new ArrayList<>();
			inDegree = new int[v+1]; // same
			path = new int[v+1]; // same
		
			// 노드 리스트 생성
			for (int i = 0; i <= v; i++) {
				graph.add(new ArrayList<>());
			}
			
			// 두 개씩 a b: a -> b
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < e; i++) {
				int prev = Integer.parseInt(st.nextToken());
				int next = Integer.parseInt(st.nextToken());
				
				graph.get(prev).add(next);
				inDegree[next]++;
			}
			
			// 위상 정렬
			sb.append('#').append(t).append(' ');
			bfs();
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	
	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		// 초기값 넣기
		for (int idx = 1; idx < inDegree.length; idx++) {
			if (inDegree[idx] == 0) {
				q.offer(idx);
				inDegree[idx]--;
			}
		}
		
		// 큐가 빌 때까지
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			sb.append(cur).append(' ');
			
			// 연결된 리스트 삭제
			for (int c : graph.get(cur)) {
				inDegree[c]--;
			}
			
			// 인접 리스트 큐에 추가
			for (int idx = 1; idx < inDegree.length; idx++) {
				if (inDegree[idx] == 0) {
					q.offer(idx);
					inDegree[idx]--; // -1로 만들기
					
				}
			}
		}
	}
}
