import java.util.*;
import java.io.*;
// N과 M (2)
// 중복 X, 오름차순
public class Main {
	static int n, m;
	static int[] result;
	
	static StringBuilder sb = new StringBuilder();
	public static void nm2(int cnt, int start) { // cnt: 위치, start: 시작지점
		if (cnt == m) {
			for (int i = 0; i < m; i++) {
				sb.append(result[i]).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i = start; i <= n; i++) {
			result[cnt] = i;
			nm2(cnt + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        result = new int[m];
        
        nm2(0, 1);
        
        System.out.println(sb);
	}
}
