import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        int[][] inputArr = new int[6][2];
        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            inputArr[i][0] = Integer.parseInt(st.nextToken());
            inputArr[i][1] = Integer.parseInt(st.nextToken());
        }
        int maxWidth = 0;
        int maxHeight = 0;
        int widthIndex = 0;
        int heightIndex = 0;

        for (int i = 0; i < 6; i++) {
            if (inputArr[i][0] == 1 || inputArr[i][0] == 2) {
                if (maxWidth < inputArr[i][1]) {
                    maxWidth = inputArr[i][1];
                    widthIndex = i;
                }
            }
            else if (inputArr[i][0] == 3 || inputArr[i][0] == 4) {
                if (maxHeight < inputArr[i][1]) {
                    maxHeight = inputArr[i][1];
                    heightIndex = i;
                }
            }
        }

        // 가장 긴 세로의 양 옆 가로 길이끼리 빼주면 작은 사각형의 가로
        int smallWidth = Math.abs(inputArr[(widthIndex + 5) % 6][1] - inputArr[(widthIndex + 1) % 6][1]);
        // 가장 긴 가로의 양 옆 세로 길이끼리 빼주면 작은 사각형의 세로
        int smallHeight = Math.abs(inputArr[(heightIndex + 5) % 6][1] - inputArr[(heightIndex + 1) % 6][1]);

        // 큰 네모 - 작은 네모
        int result =  (maxWidth * maxHeight) - (smallWidth * smallHeight);

        System.out.println(result * k);

    }
}
