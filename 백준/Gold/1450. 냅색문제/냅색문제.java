import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] obj;
    static ArrayList<Long> leftSum = new ArrayList<>();
    static ArrayList<Long> rightSum = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 물건의 개수
        C = Integer.parseInt(st.nextToken()); // 가방에 넣을 수 있는 최대 무게

        obj = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            obj[i] = Integer.parseInt(st.nextToken());
        }

        // 왼쪽 절반 부분집합 합
        makeSubSum(0, N / 2, 0, leftSum);

        // 오른쪽 절반 부분집합 합
        makeSubSum(N / 2, N, 0, rightSum);

        Collections.sort(rightSum);

        long count = 0;

        for (long sum : leftSum) {
            if (sum > C) continue;
            count += upperBound(rightSum, C - sum);
        }

        System.out.println(count);
        br.close();
    }

    private static void makeSubSum(int start, int end, long sum, ArrayList<Long> list) {
        if (sum > C) return;

        if (start == end) {
            list.add(sum);
            return;
        }

        // 현재 물건을 넣지 않는 경우
        makeSubSum(start + 1, end, sum, list);

        // 현재 물건을 넣는 경우
        makeSubSum(start + 1, end, sum + obj[start], list);
    }

    private static int upperBound(ArrayList<Long> list, long target) {
        int left = 0;
        int right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (list.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}