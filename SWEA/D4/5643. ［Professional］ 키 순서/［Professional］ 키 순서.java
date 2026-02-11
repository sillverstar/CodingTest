import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int n, m;
	static boolean[][] inOut;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			inOut = new boolean[n][n];
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				inOut[a-1][b-1] = true;
			}
			
			for (int middle = 0; middle < n; middle++) {
			    for (int i = 0; i < n; i++) {
			        for (int j = 0; j < n; j++) {
			            if (inOut[i][middle] && inOut[middle][j]) {
			                inOut[i][j] = true;
			            }
			        }
			    }
			}
			
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				int out = 0;
				int in = 0;
				for (int j = 0; j < n; j++) {
					if (inOut[i][j]) {
						out++;
					}
					if (inOut[j][i]) {
						in++;
					}
				}
				if ((out + in) == n-1) {
					cnt++;
				}
			}
			sb.append('#').append(t).append(' ').append(cnt).append('\n');
		}
		System.out.println(sb);
	}
}
