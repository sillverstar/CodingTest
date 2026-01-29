import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int T = 0; T < 10; T++) {
            int t = Integer.parseInt(br.readLine()); // 테스트 케이스 번호
            Deque<Integer> dq = new ArrayDeque<>();
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 8; i++) {
                dq.add(Integer.parseInt(st.nextToken()));
            }
            
            while (dq.getLast() != 0) {
                for (int i = 1; i <= 5; i++) {
                    int dqFirst = dq.removeFirst() - i;
                    if (dqFirst <= 0) {
                        dq.add(0);
                        break;
                    }
                    else {
                        dq.add(dqFirst);
                    }
                }    
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t);
            for (int d : dq) {
                sb.append(' ').append(d);
            }
            System.out.println(sb);
        }
    }
}