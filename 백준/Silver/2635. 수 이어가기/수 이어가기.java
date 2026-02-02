import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int cnt = (n % 2 == 0) ?  n / 2 : n / 2 + 1; // arr[1]
		int maxLength = 1;
		int maxCnt = cnt;
		while (cnt <= n) {
			List<Integer> arr = new ArrayList<>();
			arr.add(n);
			arr.add(cnt);
			
			int value = cnt; // arr[2 ~~~]
			int idx = 2;
			while (value >= 0) {
				value = arr.get(idx - 2) - arr.get(idx - 1);
				if (value < 0) {
					break;
				}
				arr.add(value);
				value = arr.get(idx);
				idx++;
			}
			
			if (maxLength < arr.size()) {
				maxLength = arr.size();
				maxCnt = cnt;
			}
			if (maxLength > arr.size()) {
				break;
			}
			cnt++;
		}
		int[] result = new int[maxLength];
		result[0] = n;
		result[1] = maxCnt;
		for (int i = 2; i < maxLength; i++) {
			result[i] = result[i - 2] - result[i - 1];
		}
        
		StringBuilder sb = new StringBuilder();
		sb.append(maxLength).append('\n');
		for (int r : result) {
			sb.append(r).append(' ');
		}
		System.out.print(sb);
	}

}
