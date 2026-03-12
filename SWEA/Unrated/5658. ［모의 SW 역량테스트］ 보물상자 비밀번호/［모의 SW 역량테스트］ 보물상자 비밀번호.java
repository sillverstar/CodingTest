import java.util.*;
import java.io.*;

public class Solution {
	static int n, k, jump;
	static String pwInput;
	static Set<Long> password;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			pwInput = br.readLine();
			pwInput += pwInput;
			
			jump = n / 4;
			
			password = new HashSet<>();
			
			for (int i = 0; i < jump; i++) {
				// 패스워드 추출 & password HashSet에 값 계산해서 넣기
				getPassword(i);
			}
			
			// password 를 ArrayList로 변환 후, 정렬하여 k번째 수 추출
			List<Long> list = new ArrayList<>(password);
			Collections.sort(list, (a, b) -> Long.compare(b, a));
			long ans = list.get(k-1);
			
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
	
	private static void getPassword(int start) {
		
		for (int i = 0; i < 4; i++) {
			String temp = pwInput.substring(start, start + jump);
			long num = Integer.parseInt(temp, 16);
			password.add(num);
			start = start + jump;
		}
	}
}
