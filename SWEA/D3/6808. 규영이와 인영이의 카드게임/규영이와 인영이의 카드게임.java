import java.io.*;
import java.util.*;

public class Solution {
	static int[] gArr, iArr;
	static int M = 9;
	static int winCnt, loseCnt; // 비기는 경우 없음
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        
        for (int t = 1; t <= T; t++) {
        	boolean[] picked = new boolean[2 * M + 1];
        	gArr = new int[M];
        	iArr = new int[M];
        	
        	winCnt = loseCnt = 0; // 초기화해주는 습관
        	
        	StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        	for (int i= 0; i < M; i++) {
        		gArr[i] = Integer.parseInt(st.nextToken());
        		picked[gArr[i]] = true;
        	} // 규영이의 카드를 저장하고 카드번호에 선택여부 true 처리
        	
        	for (int i = 1, j = 0; i < 2 * M + 1; i++) {
        		if (!picked[i]) iArr[j++] = i;
        	}
        	
//        	System.out.println(Arrays.toString(gArr));
//        	System.out.println(Arrays.toString(iArr));
//        	System.out.println("=================================================");
        	
        	permutation(0, new boolean[M], 0, 0);
        	
        	System.out.println("#" + t + " " + winCnt + " " + loseCnt);
        	
        }
        br.close();
    }
    
    
    // 인영이의 카드 순서를 결정하는 메소드(순열) : 매 라운드 경기 진행하며 점수 누적
    public static void permutation(int cnt, boolean[] isSelected, int gScore, int iScore) {
    	if (cnt == M) {
    		if (gScore > iScore) winCnt++;
    		else ++loseCnt;
    		
    		return;
    	}
    	
    	for (int i = 0; i < M; i++) {
    		if (isSelected[i]) continue;
    		isSelected[i] = true;
    		int sum = gArr[cnt] + iArr[i]; // 이 라운드에서 획득할 점수
    		int diff = gArr[cnt] - iArr[i]; //양수: 규영이 승, 음수: 인영이 승
    		permutation(cnt + 1,isSelected, diff > 0 ? gScore + sum : gScore, iScore + (diff < 0 ? sum : 0));
    		
    		isSelected[i] = false;
    		
    	}
    }
}
