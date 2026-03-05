import java.util.*;
import java.io.*;

class Edge {
	int to;
	int weight;
	
	public Edge(int to, int weight) {
		this.to = to;
		this.weight = weight;
	}
}

public class Main {
	static int n, m, ts, td;
	static List<List<Edge>> graph;
	static int[] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		// 간선 입력
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sc = Integer.parseInt(st.nextToken());
			int dc = Integer.parseInt(st.nextToken());
			int bc = Integer.parseInt(st.nextToken());
			
			graph.get(sc).add(new Edge(dc, bc));
		}
		
		st = new StringTokenizer(br.readLine());
		
		ts = Integer.parseInt(st.nextToken());
		td = Integer.parseInt(st.nextToken());
		
		dijkstra();
		
		System.out.println(dist[td]);
	}

	
	private static void dijkstra() {
		// 우선순위 큐
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		
		// 초기값 넣기(ts, cost)
		pq.offer(new int[] {ts, 0});
		dist[ts] = 0;
		
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int start = cur[0];
			int cost = cur[1];
			if (dist[start] < cost) continue;
			// 인접 정점 확인
			for (Edge next : graph.get(start)) {
				int nextCost = cost + next.weight;
				if (nextCost < dist[next.to]) {
					pq.offer(new int[] {next.to, nextCost});
					dist[next.to]= nextCost; 
				}
			}
		}
	}
}
