import java.util.*;
import java.io.*;

public class Solution {
	static boolean[] visited;
	static int[] selected;
	static int[] arr;
	static int kyuScore, inScore;
	static int kyuWin, inWin;
	
	public static void dfs(int depth) {
		if (depth == selected.length) {
			if (kyuScore > inScore) {
				kyuWin++;
			}
			if (kyuScore < inScore) {
				inWin++;
			}
			return;
		}
		
		for (int x = 1; x <= 18; x++) {
			if (visited[x]) continue;
			
			visited[x] = true;
			// 값 넣고
			selected[depth] = x;
			
			int gain = arr[depth] + selected[depth];
			// 비교
			if (arr[depth] > selected[depth]) {
				kyuScore += gain;
			}
			else if (arr[depth] < selected[depth]) {
				inScore += gain;
			}
			
			dfs(depth + 1);
			
			
			// 점수도 백트래킹을 해줘야 함
			if (arr[depth] > selected[depth]) {
				kyuScore -= gain;
			}
			else if (arr[depth] < selected[depth]) {
				inScore -= gain;
			}
			
			visited[x] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			visited = new boolean[19];
			selected = new int[9];
			kyuScore = 0;
			inScore = 0;
			kyuWin = 0;
			inWin = 0;
			
			// 입력
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = new int[9];
			
			for (int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				visited[arr[i]] = true;
			}

			dfs(0);

			sb.append('#').append(t).append(' ').append(kyuWin).append(' ').append(inWin).append('\n');
		}	
		System.out.println(sb);
	}
}