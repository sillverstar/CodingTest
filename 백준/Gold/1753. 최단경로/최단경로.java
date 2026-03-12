import java.util.*;
import java.io.*;

public class Main {

	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int v, e;
	static List<List<Edge>> adjList;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(br.readLine());
		
		// 초기값
		adjList = new ArrayList<>();
		for (int i = 0; i <= v; i++) {
			adjList.add(new ArrayList<>());
		}
		dist = new int[v+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 간선 입력
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(new Edge(to, weight));
		}

		dijkstra(start);
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= v; i++) {
			sb.append((dist[i] < Integer.MAX_VALUE) ? dist[i] : "INF").append('\n');
		}
		System.out.println(sb);
	}

	private static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {start, 0}); // 현재 정점, 누적 비용
		dist[start] = 0;
	
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			
			
			if (cur[1] != dist[cur[0]]) continue;
			
			for (Edge next : adjList.get(cur[0])) {
				int nextWeight = next.weight + cur[1];
				if (dist[next.to] > nextWeight) {
					dist[next.to] = nextWeight;
					pq.offer(new int[] {next.to, nextWeight});
				}
			}
		}
	}

}
