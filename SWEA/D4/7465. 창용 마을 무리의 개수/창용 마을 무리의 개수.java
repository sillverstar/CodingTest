import java.util.*;
import java.io.*;

public class Solution {
	static int n, m;
	static int[] parent, rank;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parent = new int[n+1];
			rank = new int[n+1];
			for (int i = 0; i <= n; i++) {
				parent[i] = i;
			}
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				union(a, b);
			}

			// 개수 세기
			Set<Integer> set = new HashSet<>();
			for (int i = 1; i <= n; i++) {
				set.add(find(i));
			}
			
			sb.append('#').append(t).append(' ').append(set.size()).append('\n');
		}
		System.out.print(sb);
	}
	
	
	private static void union(int a, int b) {
		int aR = find(a);
		int bR = find(b);
		
		if (aR == bR) return;
		
		if (rank[aR] < rank[bR]) {
			parent[aR] = bR;
		} else if (rank[bR] < rank[aR]) {
			parent[bR] = aR;
		} else if (rank[bR] == rank[aR]) {
			parent[bR] = aR;
			rank[aR]++;
		}
	}
	
	
	private static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}
}
