import java.util.*;
import java.io.*;

public class Main {
	
	static class Edge implements Comparable<Edge> {
		int to;
		int weight;
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int n, e, v1, v2;
	static List<List<Edge>> adjList;
	static final int INF = 200_000_000; // 1,000 * 200,000
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adjList.add(new ArrayList<>());
		}
		
		// 간선 입력
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(new Edge(to, weight)); // 방향이 없는 그래프
			adjList.get(to).add(new Edge(from, weight)); // 방향이 없는 그래프
		}

		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		int route1 = calculate(1, v1, v2, n);
		int route2 = calculate(1, v2, v1, n);
		
		int ans = Math.min(route1, route2);
		System.out.println((ans >= INF)? -1 : ans);
	}

	private static int calculate(int start, int v1, int v2, int end) {
		int a = getMinLen(start, v1);
		int b = getMinLen(v1, v2);
		int c = getMinLen(v2, end);
		
		if (a >= INF || b >= INF || c >= INF) {
			return INF;
		}
		return a + b + c;
	}

	private static int getMinLen(int start, int end) {
		int[] dist = new int[n+1];
		Arrays.fill(dist, INF);

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {start, 0});
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int vertex = cur[0];
			int cost = cur[1];
			
			if (cost > dist[vertex]) continue;
			
			if (vertex == end) {
				return cost;
			}
			
			for (Edge next : adjList.get(vertex)) {
				if (dist[next.to] > next.weight + cost) {
					dist[next.to] = next.weight + cost;
					pq.offer(new int[] {next.to, dist[next.to]});
				}
			}
		}
		return INF;
	}
}
