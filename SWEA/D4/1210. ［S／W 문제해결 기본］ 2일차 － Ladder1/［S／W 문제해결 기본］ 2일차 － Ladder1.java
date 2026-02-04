// 맨 마지막줄 x(2) 부터 위로 올라간다.
// 상하좌우 탐색하면서 (위로) 가다가 좌우에 길이 생기면 방향을 튼다.
// 다시 상(위쪽) 길이 나오면 위로 간다.
// 만약 row가 0일 때의 1에 도착하면 col 값을 출력 종료

// (‘0’으로 채워진 평면상에 사다리는 연속된 ‘1’로 표현된다. 도착 지점은 '2'로 표현된다).

import java.util.*;
import java.io.*;

public class Solution {
	static final int SIZE = 100;
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
        StringBuilder sb = new StringBuilder();
        
        for (int T = 1; T <= 10; T++) {
            int t = Integer.parseInt(br.readLine());
            arr = new int[SIZE][SIZE];
            
            int sr = SIZE - 1; // start row
            int sc = 0; // start col

            for (int i = 0; i < SIZE; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < SIZE; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    
                    // 마지막줄에 2 찾기
                    if (i == SIZE-1) {
                    	if (arr[i][j] == 2) {
                    		sc = j;
                    	}
                    }
                }
            }

            while (sr > 0) { // row가 0이 될 때까지 반복
                // 왼쪽 : 인덱스 먼저 확인하고 arr 접근(nullpointer)
                if (sc - 1 >= 0 && arr[sr][sc - 1] == 1) {
                	
                    while (sc - 1 >= 0 && arr[sr][sc - 1] == 1) { // 왼쪽 인덱스가 범위를 벗어나거나, 0인 경우 탈출
                        sc--; // 옆으로 이동
                    }
                    sr--; // 끝나면 위로 한 칸 올려주기. 안 올려주면 다시 왼쪽(왔던 길로) 되돌아감 ㅠ
                }
                
                // 오른쪽
                else if (sc + 1 < SIZE && arr[sr][sc + 1] == 1) {
                    while (sc + 1 < SIZE && arr[sr][sc + 1] == 1) {
                        sc++;
                    }
                    sr--; // 끝나면 위로 한 칸 올려주기
                }
                
                // 좌/우 둘 다 없으면 위로 한 칸
                else {
                    sr--;
                }
            }

            // 여기에 도달 == row가 0이 되었다.
            // row가 0일 때의 column 값 출력
            sb.append('#').append(t).append(' ').append(sc).append('\n');
        }

        System.out.print(sb.toString());
    }
}