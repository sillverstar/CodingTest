import java.util.*;
import java.io.*;

public class Solution {
	static int n, totalCnt;
	
	static boolean[] col, slash, bSlash;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			col = new boolean[n+1];
			slash = new boolean[n*2+1];
			bSlash = new boolean[n*2+1];
			totalCnt = 0;
			
			nQueen(1);
			System.out.println("#" + t + " " + totalCnt);
		}
	}

	private static void nQueen(int r) {
		if (r > n) {
			++totalCnt;
			return;
		}
		
		for (int c = 1; c <= n; c++) {
			if (col[c] || slash[r+c] || bSlash[r-c+n]) {
				continue; // 행 건너뛰기
			}
			
			// 현재 체크
			col[c] = slash[r+c] = bSlash[r-c+n] = true;
			
			nQueen(r + 1);
			
			col[c] = slash[r+c] = bSlash[r-c+n] = false;
		}
	}

}
