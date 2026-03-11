import java.util.*;
import java.io.*;

// 사이클 판단.
public class Main {
	static int n, m;
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		makeSets();
		
		// 선분 입력
		int ans = 0;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (!union(a, b) && ans == 0) {
				ans = i+1;
			}
		}
		
		System.out.println(ans);
		
	}

	private static void makeSets() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
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
