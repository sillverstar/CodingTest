import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        
        int k = Integer.parseInt(br.readLine());

        if (k > r * c) {
        	System.out.print(0);
        	return;
        }
        int top = 0, bottom = r - 1;
        int left = 0, right = c - 1;
        
        int cnt = 1;
        while (top <= bottom && left <= right) {
            // 위쪽 ↑
            for (int i = bottom; i >= top; i--) {
                if (cnt == k) {
                    int x = left + 1;
                    int y = r - i;
                    System.out.println(x + " " + y);
                    return;
                }
                cnt++;
            }
            left++;        
            
            // 오른쪽 →
            for (int i = left; i <= right; i++) {
                if (cnt == k) {
                    int x = i + 1;
                    int y = r - top;
                    System.out.println(x + " " + y);
                    return;
                }
                cnt++;
            }
            top++;

            // 아래 ↓
            for (int i = top; i <= bottom; i++) {
                if (cnt == k) {
                    int x = right + 1;
                    int y = r - i;
                    System.out.println(x + " " + y);
                    return;
                }
                cnt++;
            }
            right--;
            
            // 왼쪽 ←
            for (int i = right; i >= left; i--) {
                if (cnt == k) {
                    int x = i + 1;
                    int y = r - bottom;
                    System.out.println(x + " " + y);
                    return;
                }
                cnt++;
            }
            bottom--;
        }
    }
}
