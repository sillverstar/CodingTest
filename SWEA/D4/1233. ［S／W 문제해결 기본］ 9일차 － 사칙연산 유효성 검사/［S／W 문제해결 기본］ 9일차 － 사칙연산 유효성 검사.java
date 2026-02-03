import java.util.*;
import java.io.*;

public class Solution {

	private static boolean check(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine());
			
			// 배열 생성 + 입력
			String[] arr = new String[n + 1]; // 0은 안 씀
			for (int i = 1; i <= n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()); // index
				String param = st.nextToken();
				arr[idx] = param;
			}
	
			int ans = 1;		
			// arr[i] -> arr[2 * i] , arr[2 * i + 1]
			for (int i = 1; i <= n; i++) {
				int left = 2 * i;  //완전 이진 트리 형식으로 주어지므로 하나만 있어도 됨
				
				if ((left <= n) && !(check(arr[i]))) { // 자식이 있는데 연산자가 아니면
					ans = 0;
					break;
				} else if (!(left <= n) && (check(arr[i]))) { // 자식이 없는데 연산자면
					ans = 0;
					break;
				}	
			}
			StringBuilder sb = new StringBuilder();
			sb.append('#').append(t).append(' ').append(ans);
			System.out.println(sb);
		}
	}
}