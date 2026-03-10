import java.util.*;
import java.io.*;

public class Solution {

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
	
	static Edge[] edgeList;
	static int[] parents;
	static int V, E;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			makeSets();
			
			edgeList = new Edge[E];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				
				edgeList[i] = new Edge(s, e, w);
			}

			// 가중치 기반 정렬
			Arrays.sort(edgeList);
			
			long ans = 0;
			for (Edge edge : edgeList) {
				if (union(edge.start, edge.end)) {
					// union 성공
					ans += edge.weight;
				}
			}
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}

	
	
	// union-find
	private static void makeSets() {
		parents = new int[V+1];
		for (int i = 0; i <= V; i++) {
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
		
		if (aRoot == bRoot) return false;
		
		if (parents[aRoot] > parents[bRoot]) {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		} else {
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		}
		return true;
	}

}
