import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        
        int[] arr = new int[N+1];
        
        for (int i = 0; i <= N; i++) {
        	arr[i] = i;
        }

        int startIdx = 1;
        int endIdx = 1;
        
        int count = 1; // N이 하나만 들어있을 때
        int sum = 1; // 초기값
        
        while (startIdx != N) {
        	if (sum == N) {
        		count++;
        		endIdx++;
        		sum += arr[endIdx];
        	}
        	else if (sum > N) {
        		sum -= arr[startIdx];
        		startIdx++;
        	}
        	else if (sum < N) {
        		endIdx++;
        		sum += arr[endIdx];
        	}
        }
        
        System.out.println(count);
        
        br.close();
        

    }
}