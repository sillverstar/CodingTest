import java.util.*;
import java.io.*;

public class Main {
	static int[] first = {2, 3, 5, 7};
	static int[] after = {1, 3, 7, 9};
	
	static StringBuilder sbin = null;
	
	static void dfs(int depth, int f) {
		if (depth == 0) {
			System.out.println(f);
			return;
		}
		
		for (int a : after) {
			int next = f * 10 + a;
			if (isPrime(next)) {
				dfs(depth - 1, next);
			}
		}
	}
	
	static boolean isPrime(int num) {
		if (num == 2) return true;
		if (num < 2 || num % 2 == 0) return false;
		
		for (int i = 3; i * i <= num; i += 2) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		for (int f : first) {
			dfs(n - 1, f);
		}
	}
}