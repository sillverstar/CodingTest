import java.io.*;
import java.util.*;

public class Solution {
	static int n, maxCnt, cntValue;
	static int[][] input;
	static int[][] count;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			maxCnt = 0;
			cntValue= 0;
			
			input = new int[n][n];
			count = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					input[i][j] = Integer.parseInt(st.nextToken());
					
				}
			}
			
		    maxCnt = 0;
		    cntValue = 0;

		    for (int i = 0; i < n; i++) {
		        for (int j = 0; j < n; j++) {
		            int len = dfs(i, j);
		            int startVal = input[i][j];

		            if (len > maxCnt || (len == maxCnt && startVal < cntValue)) {
		                maxCnt = len;
		                cntValue = startVal;
		            }
		        }
		    }
		    System.out.println("#" + t + " " + cntValue + " " + maxCnt);	
		}
	}
	
	private static int dfs(int r, int c) {
	    if (count[r][c] != 0) return count[r][c]; // 이미 저장돼 있으면 재사용

	    count[r][c] = 1; // 기본: 나 자신만(길이 1)

	    int current = input[r][c];
	    for (int k = 0; k < 4; k++) {
	        int nr = r + dr[k];
	        int nc = c + dc[k];
	        
	        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

	        if (input[nr][nc] == current + 1) {
	            count[r][c] = 1 + dfs(nr, nc); // 각 단계에서 갈 수 있는 최대 깊이 저장
	        }
	    }
	    return count[r][c];
	}
}