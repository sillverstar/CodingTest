import java.util.*;
import java.io.*;

public class Solution {
	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		
			makeSet();
			
			// 간선 입력 & union
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				union(a, b);
			}
			
			// 무리 count
			int count = 0;
			for (int i = 1; i <= n; i++) {
				if (parents[i] < 0) count++;
			}
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}


	private static void makeSet() {
		parents = new int[n+1]; // 0번은 안 씀
		for (int i = 0; i <= n; i++) {
			parents[i] = -1;
		}
	}
	
	private static int find(int x) {
		if (parents[x] < 0) return x;
		return parents[x] = find(parents[x]);
	}
	
	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) return;
		
		if (parents[aRoot] > parents[bRoot]) {
			parents[bRoot] += parents[aRoot]; // 개수 갱신
			parents[aRoot] = bRoot;
		} else {
			parents[aRoot] += parents[bRoot];
			parents[bRoot] = aRoot;
		}
	}
}
