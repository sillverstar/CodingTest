import java.io.*;
import java.util.StringTokenizer;

public class Main {
    // 클래스 변수!
    // main이 static 메서드라서 static 변수만 직접 접근 가능 -> private static
    // 클래스 로딩 시점에 메모리에 딱 한 번만 생성. main 함수 내에서 선언하면 지역 변수
    private static int width;
    private static int height;
    private static int store;

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        store = Integer.parseInt(br.readLine());
        
        int[][] inputArr = new int[store + 1][3];
        // input[0] 위치 1: 북쪽 / 2: 남쪽 / 3: 서쪽 / 4: 동쪽
        // input[1] 거리 (기준점? 1, 2: 왼쪽 / 3,4: 위쪽)
        for (int i = 0; i < store + 1; i++) {
            st = new StringTokenizer(br.readLine());
            inputArr[i][0] = Integer.parseInt(st.nextToken());
            inputArr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < store + 1; i++) {
            if (inputArr[i][0] == 1) { // 북
                inputArr[i][2] = inputArr[i][1];
            }
            else if (inputArr[i][0] == 2) { // 남
                inputArr[i][2] = width + height + (width - inputArr[i][1]);
            }
            else if (inputArr[i][0] == 3) { // 서
                inputArr[i][2] = 2 * width + (2 * height - inputArr[i][1]);
            }
            else { // 동
                inputArr[i][2] = width + inputArr[i][1];
            }
        }

        int totalSum = 0;
        int total = 2 * (width + height);
        for (int i = 0; i < store; i++) {
            int distance = Math.abs(inputArr[i][2] - inputArr[store][2]);
            totalSum += Math.min(distance, total - distance);
        }
        
//        System.out.println(width + " " + height);
//        System.out.println(store);
//        System.out.println(Arrays.deepToString(inputArr));
        System.out.println(totalSum);


    }
}