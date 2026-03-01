import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] arrN, arrM;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// n, arrN 입력
		arrN = new int[n];
		for (int i = 0; i < n; i++) {
			arrN[i] = Integer.parseInt(st.nextToken());
		}

		// HashMap 선언 + 값 저장
		HashMap<Integer, Integer> dict = new HashMap<>();
		for (int i = 0; i < n; i++) {
			if (dict.get(arrN[i]) == null) {
				dict.put(arrN[i], 1);
			} else {
				dict.put(arrN[i], dict.get(arrN[i]) + 1);
			}
		}
		
		// m, arrM 입력
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arrM = new int[m];
		for (int i = 0; i < m; i++) {
			arrM[i] = Integer.parseInt(st.nextToken());
		}
		
		// 계산 + StringBuilder
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			if (dict.get(arrM[i]) == null) {
				sb.append('0').append(' ');
			} else {
				sb.append(dict.get(arrM[i])).append(' ');
			}
		}
		System.out.println(sb);
	}
}