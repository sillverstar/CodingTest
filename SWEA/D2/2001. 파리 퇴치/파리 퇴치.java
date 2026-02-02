import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n+1][n+1];
            int[][] ps = new int[n+1][n+1]; // 누적합 배열

            // 배열 입력
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 누적합 배열 생성
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                	// (1,1) 부터 (i, j)까지의 합
                	// arr[i][j] 자기 자신 + 위쪽 누적합 + 왼쪽 누적합 -겹치는 부분 누적합
                    ps[i][j] = arr[i][j] + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
                }
            }

            int max = 0;

            for (int i = 1; i <= n - m + 1; i++) {
                for (int j = 1; j <= n - m + 1; j++) {
                	// (1,1) 부터 원하는 지점까지의 큰 사각형 - 위쪽 사각형 - 왼쪽 사각형 + 왼쪽이랑 위쪽 겹치는 네모
                    int sum = ps[i+m-1][j+m-1] - ps[i-1][j+m-1] - ps[i+m-1][j-1] + ps[i-1][j-1];  
                    max = Math.max(max, sum); // 최댓값 갱신
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append('#').append(t + 1).append(' ').append(max);
            System.out.println(sb);
        }
    }
}
