import java.util.*;
import java.io.*;

public class Solution {
	static class Edge {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
	static int n, v;
	static double e;
	static boolean[] visited;
	static long[] minEdge;
	static int[][] input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			input = new int[n][2];
			// x, y
			StringTokenizer stX = new StringTokenizer(br.readLine());
			StringTokenizer stY = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				// 가중치 계산
				int x = Integer.parseInt(stX.nextToken());
				int y = Integer.parseInt(stY.nextToken());
				
				input[i] = new int[] {x, y};
			}
			
			e = Double.parseDouble(br.readLine());
			
			minEdge = new long[n];
			visited = new boolean[n];
			double ans = prim() * e;
			
			sb.append('#').append(t).append(' ').append(Math.round(ans)).append('\n');
		}
		System.out.println(sb);
	}

	private static long prim() {
		// 초기값 설정
		Arrays.fill(minEdge, Long.MAX_VALUE);
		minEdge[0] = 0;

		// 정점 개수만큼 반복
		long result = 0;
		for (int i = 0; i < n; i++) {
			// 탐색 정점 찾기
			int curIdx = -1;
			long min = Long.MAX_VALUE;
			for (int c = 0; c < n; c++) {
				if (!visited[c] && min > minEdge[c]) {
					curIdx = c;
					min = minEdge[c];
				}
			}
			
			if (curIdx == -1) break;
			visited[curIdx] = true;
			result += min;
			
			// 인접정점 탐색: 전체 다 가중치 계산해서 확인
			for (int next = 0; next < n; next++) {
				long nX = (input[curIdx][0] - input[next][0]);
				long nY = (input[curIdx][1] - input[next][1]);
				long nextWeight = nX*nX + nY*nY;
				if (!visited[next] && minEdge[next] > nextWeight) {
					minEdge[next] = nextWeight;
				}
			}
		}
		return result;
	}
}
