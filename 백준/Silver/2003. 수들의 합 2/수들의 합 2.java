import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken()); // target
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0, e = 0, cnt = 0, sum = 0;
		
		while (true) {
			if (sum >= m) {
				if (sum == m) ++cnt;
				sum -= arr[s++];
			} else if (e == n) {
				break;
			} else {
				sum += arr[e++];
			}
		}
		System.out.println(cnt);
	}
}