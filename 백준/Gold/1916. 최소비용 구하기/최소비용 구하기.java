import java.util.*;
import java.io.*;

public class Main {
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int n, m, a, b, minCost;
	static List<List<Edge>> adjList;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			adjList.add(new ArrayList<>());
		}
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList.get(from).add(new Edge(to, weight)); // 방향 X
		}
		
		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		minCost = 0;
		findMinCost(a, b);
		System.out.println(minCost);
	}

	private static void findMinCost(int start, int end) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		dist[start] = 0;
		pq.offer(new int[] {start, dist[start]});
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int now = cur[0];
			int cost = cur[1];
			
			if (dist[now] < cost) continue;

			if (now == end) {
				minCost = cost;
				return;
			}
			
			for (Edge next : adjList.get(now)) {
				if (dist[next.to] > cost + next.weight) {
					dist[next.to] = cost + next.weight;
					pq.offer(new int[] {next.to, dist[next.to]});
				}
			}
		}
	}
}
