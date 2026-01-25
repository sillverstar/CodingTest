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
        //System.out.println(Arrays.toString(arr));


        int cntMax = 0;
        List<Integer> resultDec = new ArrayList<>();
        List<Integer> resultInc = new ArrayList<>();
        resultInc.add(arr[0]);
        resultDec.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i] >= resultInc.get(resultInc.size() - 1)) {
                resultInc.add(arr[i]);
            }
            else {
                if (cntMax < resultInc.size()) {
                    cntMax = resultInc.size();
                }
                resultInc.clear();
                resultInc.add(arr[i]);
            }

            if (arr[i] <= resultDec.get(resultDec.size() - 1)) {
                resultDec.add(arr[i]);
            }
            else {
                if (cntMax < resultDec.size()) {
                    cntMax = resultDec.size();
                }
                resultDec.clear();
                resultDec.add(arr[i]);
            }
        }

        cntMax = Math.max(cntMax, resultInc.size());
        cntMax = Math.max(cntMax, resultDec.size());

//        System.out.println(Arrays.toString(resultDec.toArray()));
//        System.out.println(Arrays.toString(resultInc.toArray()));
        System.out.println(cntMax);


    }
}