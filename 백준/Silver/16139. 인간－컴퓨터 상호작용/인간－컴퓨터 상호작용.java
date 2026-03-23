import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		String S = br.readLine(); // 문자열
		int q = Integer.parseInt(br.readLine()); // 질문의 수
		
		int[][] prefixSum = new int[26][S.length()+1];
		
		
		// 누적 배열 만들기(a~z): 26자
		
		// 1. 일단 2차원 배열에 문자열 변환해서 넣기
		for (int i = 1; i <= S.length(); i++) {
			char temp = S.charAt(i-1);
			prefixSum[temp - 'a'][i]++;
		}
		
		// 2. 누적합 배열로 만들기
		for (int i = 0; i < 26; i++) {
			for (int j = 1; j <= S.length(); j++) {
				prefixSum[i][j] = prefixSum[i][j-1] + prefixSum[i][j];
			}
		}
		
		// 질문 입력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < q; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int alpha = st.nextToken().charAt(0) - 'a';
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			int ans = prefixSum[alpha][r+1] - prefixSum[alpha][l];
			sb.append(ans).append('\n');
			
		}
		System.out.println(sb);
		br.close();
	}
}