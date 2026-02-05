import java.util.*;
import java.io.*;

// 조합 문제
// 입력된 칼로리 이하로 먹을 때 점수가 가장 높은 재료의 조합을 탐색(중복x 순서x)
public class Solution {
	static int n; // 재료 수
	static int limit; // 제한 칼로리
	static int maxScore; // 최대 점수
	static int[] scores; // 점수
	static int[] cal; // 제한 칼로리
	
	public static void combination(int count, int score, int allCal) {
		if(allCal > limit) { // 칼로리가 주어진 칼로리를 넘으면 끝
			return;
		}
		
        if (count == n) { // 재료 수를 다 채우면 최대 점수 갱신
            maxScore = Math.max(maxScore, score);
            return;
        }
		
        // 현재 재료 선택하는 경우
        combination(count + 1, score + scores[count], allCal + cal[count]);

        // 현재 재료 선택 안 하는 경우
        combination(count + 1, score, allCal);
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
