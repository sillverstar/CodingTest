import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
    	
    	boolean[][] arr = new boolean[100][100]; // false으로 초기화
    	
    	// 4개의 입력
    	int cnt = 0;
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for (int t = 0; t < 4; t++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		// 1 <= x, y <= 100: 0~99
    		int x1 = Integer.parseInt(st.nextToken());
    		int y1 = Integer.parseInt(st.nextToken());
    		int x2 = Integer.parseInt(st.nextToken());
    		int y2 = Integer.parseInt(st.nextToken());
    		
    		for (int i = y1; i < y2; i++) {
    			for (int j = x1; j < x2; j++) {
    				if (arr[i][j] == false) {
    					arr[i][j] = true;
    					cnt++;
    				}
    			}
    		}
    	}
    	System.out.print(cnt);
    }
}