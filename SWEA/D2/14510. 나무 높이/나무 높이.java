import java.util.*;
import java.io.*;

public class Solution {
	static int maxTree;
	static int evens, odds;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			
			int[] tree = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				tree[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(tree);
			maxTree = tree[n - 1];
			
			evens = 0;
			odds = 0;
			
			for (int i = 0; i < n; i++ ) {
				int diff = maxTree - tree[i];
				evens += (diff / 2);
				odds += (diff % 2);
			}

			// 날짜 계산
			int ans = countDays();
			
			System.out.println("#" + t + " " + ans);
		}
	}
	
	private static int countDays() {
		int days = 0;
		while (true) {
			// 만약에 days 안에 조합이 가능하면
			if (checkDays(days)) {
				return days;
			}
			days++;
		}
	}
	
	private static boolean checkDays(int days) {
	    int oddDay = (days + 1) / 2;
	    int evenDay = days / 2;  
	    
	    if (oddDay < odds) return false; // 1 충족 여부 확인
	    
	    // 바뀌는 1 처리 4 -> x2x2(x) 121(o)
	    int evenToOdd = (oddDay - odds) / 2;
	    
	    // 2 처리
	    int newEvens = (evens > evenToOdd)? evens - evenToOdd : 0;
	    if (evenDay >= newEvens) {
	    	return true;
	    }
		return false; 
	}

}