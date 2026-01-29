import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < T; t++) {
        	int n = Integer.parseInt(br.readLine());

        	List<String> arr = new ArrayList<>(n);
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
            	arr.add(st.nextToken());
            }
            
            int start = (n % 2 == 0) ? n / 2 : n / 2 + 1;
            
            for (int idx = 1; idx < n; idx += 2) {
            	String temp = arr.remove(start);
            	arr.add(idx, temp);
            	start++;
            }
            
            StringBuilder sb = new StringBuilder();
            sb.append('#').append(t + 1);
            for (String s : arr) {
            	sb.append(' ').append(s);
            }
            System.out.println(sb);
        }
    }
}
