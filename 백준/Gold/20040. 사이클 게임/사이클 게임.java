import java.util.*;
import java.io.*;

public class Main {
	static int n, m, ans;
	static int[] parent, rank;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		rank = new int[n];
		
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
		
		ans = 0;
		
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (ans == 0 && (!union(a, b))) {
				ans = i;
			}
		}
		System.out.println(ans);
	}


	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return false;
		}
		
		if (rank[aRoot] < rank[bRoot]) {
			parent[aRoot] = bRoot;
		} else if (rank[aRoot] > rank[bRoot]) {
			parent[bRoot] = aRoot;
		} else if (rank[aRoot] == rank[bRoot]) {
			parent[bRoot] = aRoot;
			rank[aRoot]++;
			
		}
		return true;
	}

	private static int find(int x) {
		if (x == parent[x]) return x;
		return parent[x] = find(parent[x]);
	}	
}
