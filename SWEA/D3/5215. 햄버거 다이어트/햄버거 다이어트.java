import java.util.*;
import java.io.*;

public class Solution {
	static int n; // 재료 수
	static int limit; // 제한 칼로리
	static int maxScore; // 최대 점수
	static int[] scores; // 점수
	static int[] cal; // 칼로리
	
	public static void combination(int start, int score, int allCal) { // 시작 지점 결정
		if(allCal > limit) { // 칼로리가 목표치를 넘으면 끝
			return;
		}
        maxScore = Math.max(maxScore, score);

        for (int i = start; i < n; i++) { // 시작점을 +1 하면서 조합 요소 결정
        	combination(i + 1, score + scores[i], allCal + cal[i]);
        }
	}

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            limit = Integer.parseInt(st.nextToken());

            scores = new int[n];
            cal = new int[n];
            maxScore = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                cal[i] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0, 0);

            System.out.println("#" + tc + " " + maxScore);
		}
	}
}