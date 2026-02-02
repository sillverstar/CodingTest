import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int t = 0; t < 10; t++) {
        	int n = Integer.parseInt(br.readLine());
            
            // 암호문 - LinkedList 사용
            List<String> cipher = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
            	cipher.add(st.nextToken());
            }
            
            int cmdCnt = Integer.parseInt(br.readLine());
            
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cmdCnt; i++) {
            	String cmd = st.nextToken(); // 명령 입력 (I | D)
            	int param1 = Integer.parseInt(st.nextToken());
            	int param2 = Integer.parseInt(st.nextToken());
            	
                if (cmd.equals("I")) {
                	for (int j = 0; j < param2; j++) {
                		cipher.add(param1 + j, st.nextToken());
                	}
                }
                else { // cmd.equals("D")
                	for (int j = 0; j < param2; j++) {
                		cipher.remove(param1);
                	}
                } 
            }
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(t+1).append(' ');
            for (int i = 0; i < 10; i++) {
            	sb.append(cipher.get(i)).append(' ');
            }   
            System.out.println(sb);
        }       
    }
}