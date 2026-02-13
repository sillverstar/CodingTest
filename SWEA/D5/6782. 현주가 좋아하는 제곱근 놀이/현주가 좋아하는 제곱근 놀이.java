import java.io.*;

public class Solution {
	static long n, count;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			n = Long.parseLong(br.readLine());	
			count = 0;
			
            while (true) {
            	if (n == 2) break;
            	
                double sqrtN = Math.sqrt(n);
          
                if ((long) sqrtN == sqrtN) {
                    n = (long) sqrtN;
                    count++;
                }
                
                else {
                    long nextN = ((long) sqrtN + 1) * ((long) sqrtN + 1);
                    count += (nextN - n) + 1;
                    n = (long) Math.sqrt(nextN);
                }
            }
			sb.append('#').append(t).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
