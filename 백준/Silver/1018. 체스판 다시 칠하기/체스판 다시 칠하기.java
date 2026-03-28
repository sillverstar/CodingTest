import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        boolean first, expected, actual;
        int incorrect, answer = 64;

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) { // (i, j): 시작점
                incorrect = 0;
                first = (board[i][j] == 'W'); // W면 true, B이면 false

                for (int r = i; r < i + 8; r++) {
                    for (int c = j; c < j + 8; c++) { // (r, c): 블록 내부
                        expected = ((r + c) % 2 == (i + j) % 2) ? first : !first;
                        actual = (board[r][c] == 'W');

                        if (expected != actual) {
                            incorrect++;
                        }
                    }
                }

                answer = Math.min(answer, Math.min(incorrect, 64 - incorrect));
            }
        }

        System.out.println(answer);
    }
}