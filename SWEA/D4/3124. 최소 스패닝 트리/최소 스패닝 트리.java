import java.util.*;
import java.io.*;

public class Solution {
	
	static class Node {
		int vertex, weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	static int v, e;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			boolean[] visited; // MST에 포함된 정점
			int[] minEdge; // MST와 연결되는 최소 비용
			Node[] adjList; // 그래프
			
			st = new StringTokenizer(br.readLine());
			
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			
			visited = new boolean[v+1]; // 정점 초기화 (false: 비트리, true: 트리)
			minEdge = new int[v+1];
			adjList = new Node[v+1];
			
			// 간선 입력
			for (int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, w, adjList[from]);
				adjList[to] = new Node(from, w, adjList[to]);
			}
			
			Arrays.fill(minEdge, Integer.MAX_VALUE);
			minEdge[1] = 0; // 시작 위치 1
			
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
			pq.offer(new int[] {1, 0}); // {정점 번호, 가중치}
			
			long result = 0;
			int count = 0;
			while (!pq.isEmpty()) {
				// 1. 최소 정점 꺼내기
				int[] cur = pq.poll();
				int minVertex = cur[0]; // 최소 비용을 가진 정점의 인덱스
				int min = cur[1]; // 현재까지 발견한 가장 작은 간선 비용
				
				if (visited[minVertex]) continue; // 이미 MST에 포함되어 있으면 continue;
				
				// 2. MST에 추가
				visited[minVertex] = true;
				result += min;
				count++;
				
				if (count == v) break; // 정점 개수만큼 돌았으면 break;
				
				// 3. 주변 정점 갱신(minEdge)
				for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
					if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
						// 만약 MST에 포함되어 있지 않고, minVertex에서 i로 가는 가중치 값이 i로 가는 현재 최소 가중치(minEdge[i])보다 작다면 
						// 인접 여부 필요 확인 필요 X
						minEdge[temp.vertex] = temp.weight;
						pq.offer(new int[] {temp.vertex, temp.weight});
					}
				}
			}
			sb.append('#').append(t).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}
