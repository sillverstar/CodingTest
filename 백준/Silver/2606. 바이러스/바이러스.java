import java.util.*;
import java.io.*;

// graph 만들고, bfs or dfs로 개수 count

public class Main {
	static int n, c;
	static List<List<Integer>> graph;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());
		
		// 그래프 초기화
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		// 간선 입력(무방향)
		for (int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		
		}

		
		System.out.println(bfs());
		
	}

	private static int bfs() {
		// 큐 생성
		Deque<Integer> q = new ArrayDeque<>();
		
		// visited 배열
		boolean[] visited = new boolean[n+1];
		
		// 초기값 넣기: 1번 컴퓨터
		q.offer(1);
		visited[1] = true;
		
		int count = 0;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : graph.get(cur)) {
				if (visited[next]) continue;
				q.offer(next);
				visited[next] = true;
				count++;
			}
			
		}
		
		return count;
	
	}

}
