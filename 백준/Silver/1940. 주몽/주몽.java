import java.nio.Buffer;
import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); // 재료의 개수
        M = Integer.parseInt(br.readLine()); // 갑옷을 만드는 데 필요한 수

        // 재료 입력받기
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(arr);

//        System.out.println(Arrays.toString(arr));
        // 투포인터 이용: 두 개의 고유한 번호의 합
        int sp = 0;
        int ep = N-1;

        int count = 0;

        while (sp < ep) {
            int sum = arr[sp] + arr[ep];
//            System.out.println(arr[sp] + " + " + arr[ep] + " " + sum);
            if (sum == M) {
                count++;
                sp++;
                ep--;
            }
            else if (sum < M) {
                sp++;
            }
            else {
                ep--;
            }
        }

        System.out.println(count);
    }
}
