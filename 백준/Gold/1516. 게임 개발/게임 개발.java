import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		// 그래프 선언 + 초기화
		List<List<Integer>> graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		// 진입차수 배열 생성
		int[] indegree = new int[n+1];
		
		// 시간 저장 배열 생성
		int[] time = new int[n+1];
		
		// dp 배열 생성
		int[] dp = new int[n+1];
		
		// 건물 선행 관계(간선) + 시간 배열 입력 
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			time[i] = t;
			
			int temp = Integer.parseInt(st.nextToken());
			while (temp != -1) {
				graph.get(temp).add(i);
				indegree[i]++;
				temp = Integer.parseInt(st.nextToken());
			}
			
		}
		
		
		// 큐 생성
		Deque<Integer> q = new ArrayDeque<>();
		
		// indegree 0 큐에 넣기 + dp에 초기 시간 저장
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
		
		for (int i = 1; i <= n; i++) {
			sb.append(dp[i]).append('\n');
		}
		
		System.out.println(sb);
		
		
		

	}

}
