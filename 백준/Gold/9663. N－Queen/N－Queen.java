import java.util.*;
import java.io.*;

public class Main {
	static int n, totalCnt;
	static boolean[] col, slash, bSlash; // 선택할 수 없는 위치 확인

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		col = new boolean[n + 1]; // c: 1, 2, 3, ...
		slash = new boolean[2*n + 1]; // r + c: 2(1, 1), 3, ...
		bSlash = new boolean[2*n + 1]; // (c - r)+n:if n==2 1(3 - 1 + 3), 2, 3, ...
		
		totalCnt = 0;
		nQueen(1); // parameter: row
		System.out.println(totalCnt);
		
		
	}
	
	private static void nQueen(int r) {
		if (r > n) {
			++totalCnt;
			return;
		}
		
		
		for (int c = 1; c <= n; c++) {
			if (col[c] || slash[r+c] || bSlash[c-r+n]) continue;
			
			col[c] = slash[r+c] = bSlash[c-r+n] = true;
			
			nQueen(r+1);	
			
			col[c] = slash[r+c] = bSlash[c-r+n] = false;
		}
	}

}
