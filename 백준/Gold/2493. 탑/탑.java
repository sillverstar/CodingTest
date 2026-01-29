

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        int[][] inputArr = new int[T][3]; // (pos, value, ans)
        Deque<int[]> stack = new ArrayDeque<>();  // stack처럼 사용
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < T; i++) {        	
        	inputArr[i][0] = i + 1;
            inputArr[i][1] = Integer.parseInt(st.nextToken());
        }
        //System.out.println(Arrays.deepToString(inputArr));
        // System.out.println(now);
        for (int i = 0; i < T; i++) {
        	// 스택이 비어있지 않고, 꼭대기 값이 현재보다 작으면 pop;버리기
        	while (!stack.isEmpty() && stack.peek()[1] < inputArr[i][1]) {
                stack.pop();
            }
        	
        	
        	if (!stack.isEmpty()) { // 스택이 비어있지 않으면
                inputArr[i][2] = stack.peek()[0]; // pos
            }
        	// 비어있는 경우는 이미 초기값이 0으로 세팅되어 있어서 처리 x
        	stack.push(inputArr[i]);

        }

    	
        // 출력: ans만 뽑아서 출력 (문제 정답 형태)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            sb.append(inputArr[i][2]).append(' ');
        }
        System.out.print(sb);
    }
}