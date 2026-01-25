import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken()); // 세로
        int C = Integer.parseInt(st.nextToken()); // 가로

        int[][] deltasP = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 공백 없이 배열
        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        //System.out.println(Arrays.deepToString(board));

        Stack<int[]> stack = new Stack<>();

        int startMask = 1 << (board[0][0] - 'A');
        // [0]: x, [1]: y, [2]:bitmask, [3]: 얼마나 갔는지 경로
        stack.push(new int[]{0, 0, startMask, 1});

        int result = 1;

        // DFS
        while (!stack.isEmpty()) {
            int[] pop = stack.pop(); // 스택에는 다음에 탐색할 노드들만 남기는
            int x = pop[0];
            int y = pop[1];
            int mask = pop[2];
            int count = pop[3];

            result = Math.max(result, count);

            // 상하좌우 탐색해서 다음에 탐색할 노드를 스택에 넣기
            for (int i = 0; i < 4; i++) {
                int nx = x + deltasP[i][0];
                int ny = y + deltasP[i][1];

                // 범위 체크
                if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                    continue;
                }

                // nextBit: 다음 노드의 알파벳 사용 여부
                int nextBit = 1 << (board[nx][ny] - 'A'); // 해당하는 알파벳의 비트만 1로 바뀐 상태
                // 기존 mask랑 nextBit 비교: & 연산(둘 다 1일 때 1)
                if ((mask & nextBit) == 0) { // 0이면 mask의 해당 비트가 0인 것과 같으므로 사용하지 않은 것
                    stack.push(new int[]{nx, ny, mask | nextBit, count + 1});
                    // nx, xy: 위치
                    // mask | nextBit: 하나라도 1이면 1로 set. 다음 노드의 알파벳 사용 여부 추가
                    // count + 1: 경로 길이 + 1
                }
            }
        }

        System.out.println(result);
    }
}