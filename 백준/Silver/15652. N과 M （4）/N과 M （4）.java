import java.util.Scanner;

public class Main {
	static int n, m;
	static int[] arr;
	
	static StringBuilder sb;
	
	private static void nm4(int cnt, int start) {
		// 종료 조건
		if (cnt == m) {
			for (int a : arr) {
				sb.append(a).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		// 탐색
		for (int i = start; i <= n; i++) {
			arr[cnt] = i;
			nm4(cnt + 1, i);
		}
	}
	
	public static void main(String[] args) {
		// 입력 및 static 변수 초기화
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[m];
		sb = new StringBuilder();
		
		// 함수 호출
		nm4(0, 1);
		
		// 최종 출력
		System.out.println(sb);
	}
}
