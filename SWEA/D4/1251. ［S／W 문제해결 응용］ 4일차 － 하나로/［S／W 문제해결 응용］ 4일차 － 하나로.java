import java.util.*;
import java.io.*;


public class Solution {

	static class Vertex {
		long x;
		long y;
		
		public Vertex(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {
		int startIdx;
		int endIdx;
		Vertex start;
		Vertex end;
		long weight;
		
		public Edge(int startIdx, int endIdx) {
			this.startIdx = startIdx;
			this.endIdx = endIdx;
			this.start = isLands[startIdx];
			this.end = isLands[endIdx];
			long subX = Math.abs(start.x - end.x);
			long subY = Math.abs(start.y - end.y);
			this.weight = subX*subX + subY*subY;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
		
		public String toString() {
			return "(" + this.start.x + ", " + this.start.y + ") - (" + this.end.x + ", " + this.end.y + "): " + this.weight;
		}
	}

	static Vertex[] isLands;
	static Edge[] edgeList;
	static int[] parents;
	static int N;
	static double E;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			isLands = new Vertex[N];
			// x, y 입력
			StringTokenizer x = new StringTokenizer(br.readLine());
			StringTokenizer y = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				long tmpX = Long.parseLong(x.nextToken());
				long tmpY = Long.parseLong(y.nextToken());
				isLands[i] = new Vertex(tmpX, tmpY);
			}
			
			E = Double.parseDouble(br.readLine());
			
			// 간선 리스트. 중복X, 순서X
			makeEdgeList();
			
			
			Arrays.sort(edgeList);

			makeSets();
			
			double ans = 0;
			for (int i = 0; i < edgeList.length; i++) {
				if (union(edgeList[i].startIdx, edgeList[i].endIdx)) {
					ans += edgeList[i].weight * E;
				}
			}
			sb.append('#').append(t).append(' ').append(Math.round(ans)).append('\n');
		}
		System.out.println(sb);
	}
	
	
	private static void makeEdgeList() {
		edgeList = new Edge[N*(N-1) / 2];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				edgeList[idx++] = new Edge(i, j);
			}
		}
	}
	
	
	private static void makeSets() {
		parents = new int[edgeList.length+1];
		for (int i = 0; i <= edgeList.length; i++) {
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
			parents[bRoot] += parents[aRoot];
			parents[aRoot] = bRoot;
		} else {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
		return true;
	}

}
