import java.io.*;
import java.util.*;

public class Solution {
    static List<Integer> m1, m2, m3, m4;
    static List[] list;
    static int[][] rotation;
    static boolean[] visited;
    
    // 그냥 회전 함수
    private static void rotate(List<Integer> m, int direction)  {
        // 회전
        if (direction > 0) { // 시계 방향
            int temp = m.remove(7);
            m.add(0, temp);
        } else if (direction < 0) { // 반시계 방향
            int temp = m.remove(0);
            m.add(7, temp);
        }
    
        return;
    }
    
    
    private static void dfs(List<Integer>[] mList, int pos, int dir) {
        visited[pos] = true;

        // 왼쪽 자석 확인 후 회전
        int left = pos - 1;
        if (left >= 0 && !visited[left]) {
            // 현재의 6번(왼쪽 맞닿는 톱니) vs 왼쪽 자석의 2번(오른쪽 맞닿는 톱니)
            if (mList[pos].get(6) != mList[left].get(2)) {
                dfs(mList, left, -dir);
            }
        }

        // 오른쪽 자석 확인 후 회전
        int right = pos + 1;
        if (right < 4 && !visited[right]) {
            // 현재의 2번(오른쪽 맞닿는 톱니) vs 오른쪽 자석의 6번(왼쪽 맞닿는 톱니)
            if (mList[pos].get(2) != mList[right].get(6)) {
                dfs(mList, right, -dir);
            }
        }

        // 마지막에 자신 회전
        rotate(mList[pos], dir);
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 1; t <= T; t++) {
            
            // 회전 횟수 입력
            int k = Integer.parseInt(br.readLine());
            
            // 배열 입력
            m1 = new ArrayList<>(8);
            m2 = new ArrayList<>(8);
            m3 = new ArrayList<>(8);
            m4 = new ArrayList<>(8);
            
            list = new List[] {m1, m2, m3, m4};
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 8; j++) {
                    list[i].add(Integer.parseInt(st.nextToken()));
                }
            }
            
            // 회전 정보 입력
            rotation = new int[k][2];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                rotation[i][0] = Integer.parseInt(st.nextToken());
                rotation[i][1] = Integer.parseInt(st.nextToken());
            }
            
            // N: 0, S: 1
            for (int i = 0; i < k; i++) {
                int dir = rotation[i][1];
                int pos = rotation[i][0] - 1;

                visited = new boolean[4];
                dfs(list, pos, dir);
            }
            
            int sum = 0;

            if (list[0].get(0).equals(1)) sum += 1;
            if (list[1].get(0).equals(1)) sum += 2;
            if (list[2].get(0).equals(1)) sum += 4;
            if (list[3].get(0).equals(1)) sum += 8;
            
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(t).append(' ').append(sum);
            System.out.println(sb);   
        }
        br.close();
    }
}