import java.util.*;
import java.io.*;

public class Main {
	static int n, count;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		count = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int x = Integer.parseInt(st.nextToken());
			isPrime(x);
			
		}
		System.out.println(count);
	}
	private static void isPrime(int x) {
		if (x < 2) return;
		for (int i = 2; i <= Math.sqrt(x); i++) {
			if (x % i == 0) {
				return;
			}
		}
		count++;
	}
}


