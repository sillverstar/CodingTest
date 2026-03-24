import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int startIdx = 1;
		int endIdx = 1;
		
		int count = 1;
		int sum = 1; // N 자기 자신 하나로 이루어진 경우의 수 미리 저장
		
		while (endIdx != N) {
			if (sum == N) {
				count++;
				endIdx++;
				sum += endIdx;
			}
			else if (sum > N) {
				sum -= startIdx;
				startIdx++;
			}
			else if (sum < N) {
				endIdx++;
				sum += endIdx;
			}
		}
		System.out.println(count);
		br.close();
	}
}