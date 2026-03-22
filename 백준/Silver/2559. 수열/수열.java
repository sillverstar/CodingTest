import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 입력
		int N = Integer.parseInt(st.nextToken()); // 온도를 측정한 전체 날짜의 수
		int K = Integer.parseInt(st.nextToken()); // 합을 구하기 위한 연속적인 날짜의 수
		
		// 배열 입력받으면서 누적합 게산
		int[] s = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			s[i] = s[i-1] + Integer.parseInt(st.nextToken());
		}
		
		// 구간 계산 및 최댓값 갱신
		int maxTemp = (-100*100_000) -1;
		for (int i = K; i <= N; i++) {
			int temp = s[i] - s[i-K];
			maxTemp = Math.max(maxTemp, temp);
		}
		System.out.println(maxTemp);
		br.close();
	}
}