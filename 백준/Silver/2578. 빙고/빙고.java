import java.io.*;
import java.util.Arrays;

public class Main {
    private static int n = 5;
    public static int check(int[][] arr) {
        int cnt = 0;
        
        boolean diag1Zero = true; // [i][i] 왼쪽 -> 오른쪽
        boolean diag2Zero = true; // [i][n-i-1] 오른쪽 -> 왼쪽

        for (int i = 0; i < n; i++) {
            boolean rowZero = true;
            boolean colZero = true;

            // 대각선 체크
            if (arr[i][i] != 0) diag1Zero = false;
            if (arr[i][n - i - 1] != 0) diag2Zero = false;

            for (int j = 0; j < n; j++) {
                // 가로
                if (arr[i][j] != 0) rowZero = false;

                // 세로
                if (arr[j][i] != 0) colZero = false;

                // 둘 다 실패하면 더 볼 필요 없음
                if (!rowZero && !colZero) break;
            }

            if (rowZero) cnt++;
            if (colZero) cnt++;
        }

        if (diag1Zero) cnt++;
        if (diag2Zero) cnt++;

        return cnt;
    }


    public static void main(String[] args) throws IOException {
        // 1) 입력: BufferedReader
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int[][] inputArr = new int[n][n]; // 입력 배열

        // - 입력 배열 값 저장

        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                inputArr[i][j] = Integer.parseInt(line[j]);
            }
        }
        
        // 배열 테스트 출력용
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(inputArr[i][j] + " ");
//            }
//            System.out.println();
//        }

        // System.out.println(Arrays.toString(outputArr));
        
        // 사회자 입력
        int[] numbers = new int[n * n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            String[] line = bf.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                numbers[idx++] = Integer.parseInt(line[j]);
            }
        }

        //System.out.println(Arrays.toString(numbers));

        
        // 사회자 입력에 따라 0으로 변경
        for (int k = 0; k < n * n; k++) {
            int called = numbers[k];

            // 숫자를 찾아 0으로 변경
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (inputArr[i][j] == called) {
                        inputArr[i][j] = 0;
                    }
                }
            }
            // 빙고 체크
            int bingoCnt = check(inputArr);

            // 3빙고 이상
            if (bingoCnt >= 3) {
                System.out.println(k + 1);
                break;
            }
        }
    }
}