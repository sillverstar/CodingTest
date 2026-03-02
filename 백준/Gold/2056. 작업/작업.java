import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		// 수행해야 할 작업 N
		int n = Integer.parseInt(br.readLine());
		
		// 그래프 선언 + 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 진입 차수 배열 저장
		int[] indegree = new int[n+1];
		
		// 작업별로 걸리는 시간 저장
		int[] time = new int[n+1];
		
		// 작업별 선행 관계 입력(간선)
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			
			int m = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < m; j++) {
				int temp = Integer.parseInt(st.nextToken());
				
				graph.get(temp).add(i);
				indegree[i]++;
			}
		}
		
		// 큐 생성
		Deque<Integer> q = new ArrayDeque<>();
		
		// dp 배열 생성
		int[] dp = new int[n+1];
		
		
		// 진입 차수 0인 경우 큐에 넣기
		// + 초기 dp 값 넣기
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				dp[i] = time[i];
			}
		}
		
		
		// 위상정렬
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph.get(cur)) {
				indegree[next]--;
				
				dp[next] = Math.max(dp[next], dp[cur] + time[next]);
				
				if (indegree[next] == 0) {
					q.offer(next);
				}
			}
		}
		
		// 최종 값
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			ans = Math.max(ans, dp[i]);
		}
		System.out.println(ans);
		
	}

}
