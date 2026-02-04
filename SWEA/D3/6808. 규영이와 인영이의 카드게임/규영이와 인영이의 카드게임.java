import java.util.*;
import java.io.*;

public class Solution {
	static boolean[] kyuVisited;
	static boolean[] inVisited;
	static int[] kyuArr;
	static int[] inArr;
	static int kyuScore, inScore;
	static int kyuWin, inWin;
	
	public static void dfs(int depth) {
		if (depth == 9) {
			if (kyuScore > inScore) {
				kyuWin++;
			}
			else if (kyuScore < inScore) {
				inWin++;
			}
			return;
		}
		
		for (int x = 0; x < 9; x++) {
			if (inVisited[x]) continue;
			
			inVisited[x] = true;
			int in = inArr[x];
			
			int gain = kyuArr[depth] + in;
			// 비교
			if (kyuArr[depth] > in) {
				kyuScore += gain;
			}
			else if (kyuArr[depth] < in) {
				inScore += gain;
			}
			
			dfs(depth + 1);
			
			
			// 점수도 백트래킹을 해줘야 함
			if (kyuArr[depth] > in) {
				kyuScore -= gain;
			}
			else if (kyuArr[depth] < in) {
				inScore -= gain;
			}
			
			inVisited[x] = false;
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			kyuVisited = new boolean[19];
			inVisited = new boolean[9];
			
			kyuScore = 0;
			inScore = 0;
			kyuWin = 0;
			inWin = 0;
			
			// 규영 카드(입력)
			StringTokenizer st = new StringTokenizer(br.readLine());
			kyuArr = new int[9];
			
			for (int i = 0; i < 9; i++) {
				kyuArr[i] = Integer.parseInt(st.nextToken());
				kyuVisited[kyuArr[i]] = true;
			}
			
			// 인영 카드(생성)
			inArr = new int[9];
			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if (!kyuVisited[i]) {
					inArr[idx++] = i;
				}
			}

			dfs(0);

			sb.append('#').append(t).append(' ').append(kyuWin).append(' ').append(inWin).append('\n');
		}	
		System.out.println(sb);
	}
}