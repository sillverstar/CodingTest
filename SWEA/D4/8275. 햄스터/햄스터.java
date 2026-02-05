import java.util.*;
import java.io.*;

public class Solution {
    static int n, x, m;
    static int[] arr, result;
    static int[][] record;
    static int max_sum;
    
    public static void dfs(int cnt) {
        if(cnt == n) { // 배열 다 채워졌을 때 끝
            for(int i = 0; i < m; i++) {
                int check_sum = 0;
                for(int j = record[i][0]; j <= record[i][1]; j++) {
                    check_sum += arr[j];
                }
                if (check_sum != record[i][2]) return;
            }
 
            int sum = 0;
            for(int i = 0; i < n; i++) {
                sum += arr[i];
            }
            if(sum > max_sum) {
                max_sum = sum;
                result = Arrays.copyOf(arr, n); // 복붙
            }
            return;
        }
 
        for(int i = 0; i <= x; i++) {
            arr[cnt] = i;
            dfs(cnt+1);
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // 햄스터 우리
            x = Integer.parseInt(st.nextToken()); // 햄스터
            m = Integer.parseInt(st.nextToken()); // 기록
 
            arr = new int[n]; // 우리 배열
            record = new int[m][3]; // 기록
            result = new int[n]; // 결과 배열
 
            max_sum = -1;
 
            // 입력
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                record[i][0] = Integer.parseInt(st.nextToken()) - 1;
                record[i][1] = Integer.parseInt(st.nextToken()) - 1;
                record[i][2] = Integer.parseInt(st.nextToken());
            }
 
            dfs(0);
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("#").append(t).append(" ");

            if (max_sum == -1) {
                sb.append(-1);
            } else {
                for (int i = 0; i < n; i++) {
                    sb.append(result[i]).append(" ");
                }
            }
            System.out.println(sb);
        }
    }
}