import java.util.*;
import java.io.*;

public class Main {
	// 1. 사용할 변수 선언
	static int first, second;
	
	static int maxCnt, maxSecond;
	
	private static int recurCal(int cnt, int fir, int sec) {
		int third = 0;
		while (fir - sec >= 0) {
			third = fir - sec;
			fir = sec;
			sec = third;
			cnt++;
		}
		return cnt;
	}
	
	private static void recurPrint(int fir, int sec) {
		StringBuilder sb = new StringBuilder();
		sb.append(fir).append(' ');
		sb.append(sec).append(' ');
		int third = 0;
		while (fir - sec >= 0) {
			third = fir - sec;
			fir = sec;
			sec = third;
			sb.append(third).append(' ');
		}
		System.out.println(sb);	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 2. 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		first = Integer.parseInt(br.readLine());
		
		// 3. static 변수 초기화
		second = 0;
		maxCnt = 2; // first, second가 이미 들어가있음
		maxSecond = 0;
		
		// 4. 두 번째 수를 바꿔가면서 recurCal 함수 호출
		for (int s = first / 2; s <= first; s++) {
			int cnt = recurCal(2, first, s);
			
			//maxCnt & maxSecond 갱신
			if (cnt > maxCnt) {
				maxCnt = cnt;
				maxSecond = s;
			}
		}
		
		// 5. maxCnt를 출력한다.
		System.out.println(maxCnt);
		
		// 6. maxSecond를 이용해서 수들를 출력하는 함수 recurPrint를 호출한다.
		recurPrint(first, maxSecond);
	}
}
