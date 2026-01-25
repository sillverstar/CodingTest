import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // 수열의 길이 입력

        int[] arr = new int[n]; // 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cntMax = 1;

        int cntInc = 1;
        int cntDec = 1;
        for (int i = 1; i < n; i++) {
            // 중가
            if (arr[i] >= arr[i - 1]) {
                cntInc++;
            }
            else {
                cntInc = 1;
            }

            // 감소
            if (arr[i] <= arr[i - 1]) {
                cntDec++;
            }
            else {
                cntDec = 1;
            }
            cntMax = Math.max(cntMax, cntInc);
            cntMax = Math.max(cntMax, cntDec);
        }

//        System.out.println(Arrays.toString(resultDec.toArray()));
//        System.out.println(Arrays.toString(resultInc.toArray()));
        System.out.println(cntMax);


    }
}