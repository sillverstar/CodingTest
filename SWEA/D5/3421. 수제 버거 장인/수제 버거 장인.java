import java.io.*;
import java.util.*;

public class Solution {
	static int n, m; // 재료 수(n), 궁합이 맞지 않는 재료 수(m)
	static int[] mList; // 궁합이 맞지 않는 재료 리스트(비트마스크 이용: n의 최댓값이 20이므로 int로)
	static int ans;

	public static void subset(int cnt, int hambook) { // cnt: 현재 위치, hambook: 현재까지 누적된 버거 재료
		if (cnt > n) {
			++ans;
			return;
		}
		
		// 선택하는 경우 - 제약 조건(mList 확인)
		if ((mList[cnt] & hambook) == 0) {
			// & 연산했을 때 비트가 겹치면 0이 될 수 없음
			// -> 같은 비트가 제약사항(mList)로 있으면 추가하지 않음
			subset(cnt + 1, hambook | (1 << (cnt - 1)));
		}
		
		// 선택하지 않는 경우
		subset(cnt + 1, hambook);
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			
			mList = new int[n + 1]; // 1번째부터 사용
			
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int num1 = Integer.parseInt(st.nextToken());
				int num2 = Integer.parseInt(st.nextToken());
				
				mList[num1] |= (1 << (num2 - 1));
				mList[num2] |= (1 << (num1 - 1));
			}
			
			ans = 0;
			
			subset(1, 0);
			
			
			System.out.println("#" + t + " " + ans);
		}
	}
}