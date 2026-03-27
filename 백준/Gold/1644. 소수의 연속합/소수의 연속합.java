import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		List<Integer> arr = new ArrayList<>();
		// 배열 생성
		for (int i = 2; i <= N; i++) {
			if (isPrime(i)) arr.add(i);
		}
		
		
		// 투포인터
		int startIdx = 0;
		int endIdx = 0;
		
		int count = 0;
		int sum = 0;
		
		while (true) {
			if (sum >= N) {
				if (sum == N) count++;
				sum -= arr.get(startIdx);
				startIdx++;
			}
			else {
				if (endIdx == arr.size()) break;
				sum += arr.get(endIdx);
				endIdx++;
			}
		}
		
		
		System.out.println(count);
		br.close();
	}

	private static boolean isPrime(int num) {
		if (num < 2) return false;
		
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}
}