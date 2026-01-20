import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
public class Main {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        int n = Integer.parseInt(line[0]);
        int m = Integer.parseInt(line[1]);
        int r = Integer.parseInt(line[2]);

        // 배열 입력
        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            line = bf.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(line[j]);
            }
        }

        int top = 0, bottom = n - 1;
        int left = 0, right = m - 1;


        Deque<Integer> dq = new ArrayDeque<>();

        while ((top < bottom) && (left < right)) {
            // dq에 넣기
            // 오른쪽 →
            for (int i = left; i <= right; i++) {
                dq.addLast(arr[top][i]);
            }

            // 아래 ↓
            for (int i = top + 1; i <= bottom; i++) {
                dq.addLast(arr[i][right]);
            }

            // 왼쪽 ←
            for (int i = right - 1; i >= left; i--) {
                dq.addLast(arr[bottom][i]);
            }

            // 위쪽 ↑
            for (int i = bottom - 1; i > top; i--) {
                dq.addLast(arr[i][left]);
            }



            //System.out.println(dq);
            // 회전
            for (int i = 0; i < r; i++) {
                dq.addLast(dq.pollFirst());
            }

            // 다시 돌려서 넣어주기
            // 오른쪽 →
            for (int i = left; i <= right; i++) {
                arr[top][i] = dq.pollFirst();
            }

            // 아래 ↓
            for (int i = top + 1; i <= bottom; i++) {
                arr[i][right] = dq.pollFirst();
            }

            // 왼쪽 ←
            for (int i = right - 1; i >= left; i--) {
                arr[bottom][i] = dq.pollFirst();
            }

            // 위쪽 ↑
            for (int i = bottom - 1; i > top; i--) {
                arr[i][left] = dq.pollFirst();
            }

            top++;
            right--;
            bottom--;
            left++;
            //System.out.println(dq);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}