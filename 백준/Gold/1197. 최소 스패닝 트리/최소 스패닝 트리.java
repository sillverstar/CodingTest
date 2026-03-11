import java.util.*;
import java.io.*;

// 최소 스패닝 트리
public class Main {
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	static int v, e;
	static Edge[] edgeList;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		makeSet();
		
		// 간선 입력
		edgeList = new Edge[e];
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(a, b, w);
		}
		
		// 정렬
		Arrays.sort(edgeList);
		
		// 가중치 계산
		int count = 0;
		long totalWeight = 0;
		for (int i = 0; i < e; i++) {
			if (union(edgeList[i].start, edgeList[i].end)) {
				totalWeight += edgeList[i].weight;
				
				if (++count == v-1) break; // 간선이 v-1개 모두 선택되면 종료
			}
		}
		
		System.out.println(totalWeight);
	}

	private static void makeSet() {
		parents = new int[v+1];
		for (int i = 0; i <= v; i++) {
			parents[i] = -1;
		}
	}

	private static int find(int x) {
		if (parents[x] < 0) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			// 사이클 발생
			return false;
		}
		
		if (parents[aRoot] > parents[bRoot]) {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		} else {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		return true;
		
	}
}
