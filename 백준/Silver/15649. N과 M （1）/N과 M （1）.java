import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] selected;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	static void dfs(int depth) {
		if (depth == m) {
			for (int s : selected) {
				sb.append(s).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int x = 1; x <= n; x++) {
			if (visited[x]) continue;
			
			visited[x] = true;
			selected[depth] = x;
			
			dfs(depth + 1);
			
			visited[x] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		selected = new int[m];
		visited = new boolean[n + 1];
		dfs(0);
		System.out.println(sb);
	}
}