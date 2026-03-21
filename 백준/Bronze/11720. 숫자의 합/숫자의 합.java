import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		br.readLine();
		// 문자열로 입력
		String s = br.readLine();
		
		// 배열로 변환
		char[] arr = s.toCharArray();
		
		// 계산
		int ans = 0;
		for (char a : arr) {
			ans += a - 48;
		}
		
		// 츌력
		System.out.println(ans);
	}
}
