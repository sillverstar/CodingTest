import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int target = Integer.parseInt(br.readLine());
		
		int startIdx = 0;
		int endIdx = N - 1;
		int count = 0;
		
		while (startIdx < endIdx) {
			int sum = arr[startIdx] + arr[endIdx];
			
			if (sum == target) {
				count++;
				startIdx++;
				endIdx--;
			} else if (sum > target) {
				endIdx--;
			} else {
				startIdx++;
			}
		}
		
		System.out.println(count);
	}
}