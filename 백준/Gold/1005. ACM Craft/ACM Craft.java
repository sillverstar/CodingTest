import java.util.*;
import java.io.*;

public class Main {
	static int n, k, w;
	static List<List<Integer>> graph;
	static int[] indegree, time, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			indegree = new int[n+1];
			time = new int[n+1];
			dp = new int[n+1];
			
			// 시간 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}
			
			// 건설 순서(간선) 입력
			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				graph.get(x).add(y); // 순서: x -> y
				indegree[y]++;
			}
			
			w = Integer.parseInt(br.readLine());
			
			
			// 큐 생성
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			// indegree 0 넣기 + dp에 초기 시간 넣기
			for (int i = 1; i <= n; i++) {
				if (indegree[i] == 0) {
					pq.offer(i);
					dp[i] = time[i];
				}
			}
			
			
			// 위상정렬
			while (!pq.isEmpty()) {
				int cur = pq.poll();
				
				// 인접 정점 탐색
				for (int next : graph.get(cur)) {
					indegree[next]--;
					
					dp[next] = Math.max(dp[next], dp[cur] + time[next]);
					if (indegree[next] == 0) {
						pq.offer(next);
					}
				}
			}
			sb.append(dp[w]).append('\n');
		}
		System.out.println(sb);
	}

}
