import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 표의 크기
        int m = Integer.parseInt(st.nextToken()); // 구해야 하는 횟수

        // ps[0][0] 라인은 0으로 초기화해서 누적합 배열 계산할 때 씀. 1부터 사용
        int[][] ps = new int[n+1][n+1]; // 누적합 배열

        // 배열 입력 받으면서 누적합 배열로 만들기
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
            	// 자기 자신(지금 입력받는 거) + 위쪽 누적합 + 왼쪽 누적합 -겹치는 부분 누적합
                ps[i][j] = Integer.parseInt(st.nextToken()) + ps[i-1][j] + ps[i][j-1] - ps[i-1][j-1];
            }
        }

        int[] result = new int[m];
        for (int i = 1; i <= m; i++) {
        	st = new StringTokenizer(br.readLine());
        	// x1, y1
        	int x1 = Integer.parseInt(st.nextToken());
        	int y1 = Integer.parseInt(st.nextToken());
        	
        	// x2, y2
        	int x2 = Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
        	
            // (1,1) 부터 원하는 지점까지의 큰 사각형 - 위쪽 사각형 - 왼쪽 사각형 + 왼쪽이랑 위쪽 겹치는 네모
            int sum = ps[x2][y2] - ps[x2][y1-1] - ps[x1-1][y2] + ps[x1-1][y1-1];  
            result[i - 1] = sum;
        }

        for (int r : result) {
        	System.out.println(r);
        }
        
    }
}
