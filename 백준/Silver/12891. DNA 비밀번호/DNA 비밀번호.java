import java.io.*;
import java.util.*;

public class Main {
	
	// 만들 수 있는 비밀번호인지 확인
	static boolean check(int[] now, int[] acgt) {
		for (int i = 0; i < 4; i++) {
			if (now[i] < acgt[i]) { // 하나라도 충족 안되면 false
				return false;
			}
		}
		return true;
	}
	
	// ACGT에 따른 배열 위치 반환
	static int idx(char c) {
		if (c == 'A') { return 0; }
		else if (c == 'C') { return 1; }
		else if (c == 'G') { return 2; }
		else { return 3; }
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int s = Integer.parseInt(st.nextToken()); // 전체 길이
		int p = Integer.parseInt(st.nextToken()); // 윈도우 크기
		
		// 문자열 저장
		String dna = br.readLine();
		
		int[] acgt = new int[4]; // A C G T 최소 개수
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			acgt[i] = Integer.parseInt(st.nextToken());
		}
	
		int cnt = 0;
		int[] now  = new int[4]; // 현재 윈도우의 A C G T 개수

		// 초기 윈도우
		for (int i = 0; i < p; i++) {
			char c = dna.charAt(i);
			now[idx(c)]++;

		}
		
		// 비밀번호 되나 체크
		if (check(now, acgt)) {
			cnt++;
		}
		
		// 나머지
		for (int i = p; i < s; i++) {
			// 한 개 빼고 now 업데이트
			char out = dna.charAt(i - p);
			now[idx(out)]--;

			// 한 개 넣고 now 업데이트
			char in = dna.charAt(i);
			now[idx(in)]++;
			
			// 비밀번호 되나 체크
			if (check(now, acgt)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
