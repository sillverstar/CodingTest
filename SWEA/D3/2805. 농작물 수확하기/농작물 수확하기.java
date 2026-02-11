import java.io.*;

public class Solution {
	static int n, sum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			
			n = Integer.parseInt(br.readLine());
			sum = 0;
			int mm = n/2 + 1;
			for (int i = 0; i < n; i++) {
				String line = br.readLine();
				int m = (i < mm) ? (n - i) : ((i+1) % mm);
				for (int j = 0; j < n; j++) {
					int now = line.charAt(j) - '0';
					if ((m % mm) <= j && j < n - (m%mm)) {
//						System.out.println(" now :" + now);
						sum += now;
					}
				}
//				System.out.println();
			}
			
			sb.append('#').append(t).append(' ').append(sum).append('\n');
		}
		System.out.println(sb);	
	}
}
