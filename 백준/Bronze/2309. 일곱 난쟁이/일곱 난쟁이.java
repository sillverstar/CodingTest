import java.io.*;
import java.util.Arrays;

public class Main {
    // 난쟁이 아닌 애들 찾는 함수
    private static int[] findNotDwarf(int[] arr) {
        int[] notDwarf = new int[2];
        // Arrays.stream의 sum 메서드 이용해서 합계 구하기
        int totalSum = Arrays.stream(arr).sum();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // 정답이 여러 개면 아무거나 출력하므로 가장 첫 번째 찾은 답으로 출력
                if ((totalSum - arr[i] - arr[j]) == 100) {
                    notDwarf[0] = i;
                    notDwarf[1] = j;
                    return notDwarf;
                }
            }
        }
        return null;

    }
    public static void main(String[] args) throws IOException {
        // 1) 입력: BufferedReader
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        // 2) 정렬
        Arrays.sort(arr);

        //System.out.println(Arrays.toString(arr));

        // 3) 함수 호출
        int[] notDwarf = findNotDwarf(arr);

        // 4) 난쟁이들 출력
        for (int i = 0; i < 9; i++) {
            if (i != notDwarf[0] && i != notDwarf[1]) {
                System.out.println(arr[i]);
            }
            else {
                continue;
            }
        }
    }
}