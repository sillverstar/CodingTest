import java.util.*;
import java.io.*;

public class Main {
	static int n, minPop;
	static List<List<Integer>> graph;
	static int[] population;
	// 부분집합 선택
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		// 그래프 초기화
		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		
		// 인구수 입력
		population = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		
		// 간선 입력
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int cnt = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < cnt; j++) {
				int adj = Integer.parseInt(st.nextToken());
				graph.get(i).add(adj);
			}
		}
		
//		// 출력 테스트
//		for (int i = 1; i <= n; i++) {
//			System.out.println(graph.get(i));
//		}
		
		minPop = Integer.MAX_VALUE;
		selected = new boolean[n+1];
		subset(1);
		
		
		System.out.println((minPop == Integer.MAX_VALUE) ? -1 : minPop);
		
	}

	private static void subset(int idx) {
		// 종료 조건
		if (idx == n+1) {
//			// 출력 테스트
//			for (int i = 1; i <= n; i++) {
//				if (selected[i]) {
//					System.out.print(i + " ");
//				}
//			}
//			System.out.println();
			
			
			List<Integer> A = new ArrayList<>();
			List<Integer> B = new ArrayList<>();
			
			for (int i = 1; i <= n; i++) {
				if (selected[i]) {
					A.add(i);
				}else {
					B.add(i);
				}
			}
			
			// 선거구가 비지 않도록 체크 후 비어있다면 다음 단계를 진행하지 않으므로 리턴
			if (A.size() == 0 || B.size() == 0) return;
			
			// 두 선거구가 각각 가능할 경우(연결되어 있는 경우)
			if (isConnected(A, true) && isConnected(B, false)) {
				// 여기에 들어왔다 == 각 구역이 모두 연결되어 있다.
				// 최종적으로 구하고자 하는 것은 두 선거구의 인구 차이의 최솟값.
				// 따라서 이곳에서 인구 차이 계산을 한 후 최솟값을 갱신한다.
				int sumA = 0;
				int sumB = 0;
				
				for (int a : A) {
					sumA += population[a];
				}
				for (int b : B) {
					sumB += population[b];
				}
				
				minPop = Math.min(minPop, Math.abs(sumA-sumB));
			}
			
			
			return; // 기본 return
		}
		selected[idx] = true;
		subset(idx+1);
		
		selected[idx] = false;
		subset(idx+1);
		
	}

	private static boolean isConnected(List<Integer> group, boolean flag) {
		// group 안에 있는 애들을 bfs로 연결되어 있는지 파악을 한다.
		// 큐 생성
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1]; // bfs에서 방문한 구역을 체크하기 위한 배열
		
		// 큐에 초기값 넣기
		// 무엇을 넣을 것인가? 파라미터로 들어온 group 배열의 첫 번째 값.
		int start = group.get(0);
		q.offer(start);
		visited[start] = true;
		
		// bfs로 방문한 구역 수: 최종적으로 선거구 전체 길이와 비교하여 하나의 선거구가 모두 연결되어 있는지 파악
		int count = 1; // 현재 start를 넣었기 때문에 1부터 시작
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			// 현재 꺼낸 값의 인접 정점 확인
			for (int next : graph.get(cur)) {
				if (visited[next]) continue; // 만약 이미 방문한 거라면 지나치기
				if (selected[next] != flag) continue; // 현재 주어진 flag(구역 표시)와 다른 경우 pass
				
				// 그게 아니라면 현재 플래그와 같고(=같은 구역이고) 방문하지 않은 정점.
				visited[next] = true;
				q.offer(next);
				count++;
				
			}
		}
		
		// 개수 맞는지 확인
		if (count == group.size()) {
			return true;
		}else {
			return false;
		}
	
	}
	
	

}
