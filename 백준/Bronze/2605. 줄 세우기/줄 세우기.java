import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1) 입력: BufferedReader
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        int[] inputArr = new int[n]; // 입력 배열
        int[] outputArr = new int[n]; // 출력 배열

        // - 입력 배열 값 저장
        String[] line = bf.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            inputArr[i] = Integer.parseInt(line[i]);
        }

        // 2) 출력 배열 만들기
        for (int i = 1; i < n + 1; i++) {
            outputArr[i-1] = i;
        }

        // System.out.println(Arrays.toString(inputArr));
        // System.out.println(Arrays.toString(outputArr));

        // 3) 입력 배열 돌리면서 출력 배열 바꾸기(교환)
        for (int i = 1; i < n; i++) { // 0번째는 고정. 1 ~ n
            for (int j = 0; j < inputArr[i]; j++) {
                int temp = outputArr[i - j - 1];
                outputArr[i - j - 1] = outputArr[i - j];
                outputArr[i - j] = temp;
                //System.out.println(Arrays.toString(outputArr));
            }
            //System.out.println();
        }

        for (int out : outputArr) {
            System.out.print(out + " ");
        }
    }
}