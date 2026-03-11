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
		
		makeSets();
		
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
		int totalWeight = 0;
		for (int i = 0; i < e; i++) {
			if (union(edgeList[i].start, edgeList[i].end)) {
				totalWeight += edgeList[i].weight;
			}
		}
		
		System.out.println(totalWeight);
	}

	private static void makeSets() {
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
