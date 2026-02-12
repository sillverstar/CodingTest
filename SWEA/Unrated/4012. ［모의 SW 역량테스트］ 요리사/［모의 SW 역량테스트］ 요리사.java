import java.util.*;
import java.io.*;
// 조합 문제!
// 시너지 S

public class Solution {
    static int n;
    static int[][] S;
    static boolean[] pick; // true면 A음식
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine().trim());
            
            S = new int[n][n];

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            pick = new boolean[n];
            ans = Integer.MAX_VALUE;

            pick[0] = true; // 선택한 거랑 선택 안한 경우가 같아서 0번째를 기준으로 표시
            dfs(1, 1);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
    }

    private static void dfs(int idx, int cnt) {
        if (cnt == n / 2) {
        	int newAns = calcDiff();
            ans = Math.min(ans, newAns);
            return;
        }
        if (idx == n) return; // 선택할 재료 X

        // A음식 선택
        pick[idx] = true;
        dfs(idx + 1, cnt + 1);

        // A음식 선택 X = B음식 선택
        pick[idx] = false;
        dfs(idx + 1, cnt);
    }

    private static int calcDiff() {
        int aScore = 0, bScore = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
            	int score = S[i][j] + S[j][i]; // 시너치 총합
                
                if (pick[i] && pick[j]) aScore += score;
                else if (!pick[i] && !pick[j]) bScore += score;
            }
        }
        return Math.abs(aScore - bScore);
    }

}