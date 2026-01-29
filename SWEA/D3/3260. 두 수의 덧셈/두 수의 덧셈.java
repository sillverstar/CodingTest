import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			BigInteger a = new BigInteger(st.nextToken());
			BigInteger b = new BigInteger(st.nextToken());
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t+1).append(' ').append(a.add(b));
			System.out.println(sb);
		}
	}
}
