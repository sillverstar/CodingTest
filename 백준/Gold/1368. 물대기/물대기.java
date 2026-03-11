import java.util.*;
import java.io.*;
// Prim
public class Main {
	static int n;
	static int[][] adjMatrix;
	static int[] minEdge;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		
		adjMatrix = new int[n+1][n+1];
		minEdge = new int[n+1];
		visited = new boolean[n+1];
		
		// 논을 파는 경우 인덱스 0을 이용하여 추가
		for (int i = 1; i <= n; i++) { // 1번째 논부터 n번째 논
			int cost = Integer.parseInt(br.readLine());
			adjMatrix[i][0] = cost;
			adjMatrix[0][i] = cost;
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;
		
		long result = 0;
		for (int c = 0; c <= n; c++) {
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			
			for (int i = 0; i <= n; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}
			
			if (minVertex == -1) break;
			
			visited[minVertex] = true;
			result += min;
			
			
			for (int i = 0; i <= n; i++) {
				if (!visited[i] && adjMatrix[minVertex][i] != 0 && minEdge[i] > adjMatrix[minVertex][i]) {
					minEdge[i] = adjMatrix[minVertex][i];
				}
			}
		}
		System.out.println(result);
	}
		
}
