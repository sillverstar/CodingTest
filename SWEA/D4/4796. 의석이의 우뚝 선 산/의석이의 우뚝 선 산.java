import java.util.Scanner;

public class Solution {
	static int n;
	static int[] height;
	
	static int maxCnt;

	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		

		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = sc.nextInt();
			height = new int[n];
			maxCnt = 0;
			
			for (int i = 0; i < n; i++) {
				height[i] = sc.nextInt();
			}
			// System.out.println(Arrays.toString(height));
			sb.append('#').append(t).append(' ' );
			
			totalHCount();

			sb.append(maxCnt).append('\n');
		}
		System.out.println(sb);
		
	}

	private static void totalHCount() {
	    int asc = 0;
	    int desc = 0;
	   
	    for (int i = 1; i < n; i++) {
	    	if (height[i-1] < height[i]) { // /
	    		if (desc > 0) { // /\/ 일 때 : \ -> desc > 0
	    			maxCnt += (asc * desc);
	    			asc = 0;
	    			desc = 0;
	    		}
	    		asc++;
	    	}
	    	else { // \
	    		if (asc > 0) { // /\ 일 때만 ++, \ pass
	    			desc++;
	    		}	
	    	}
	    }
	    maxCnt += (desc > 0)? (asc * desc) : 0; // 마지막이 desc
	}
}
