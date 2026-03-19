import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] type = new int[n];
        int[] value = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            type[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            value[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> deque = new ArrayDeque<>();

        // 큐(0)인 자료구조의 초기값만 순서대로 저장
        for (int i = 0; i < n; i++) {
            if (type[i] == 0) {
                deque.offerLast(value[i]);
            }
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int x = Integer.parseInt(st.nextToken());

            if (deque.isEmpty()) {
                sb.append(x).append(' ');
            } else {
                deque.offerFirst(x);
                sb.append(deque.pollLast()).append(' ');
            }
        }

        System.out.println(sb);
    }
}