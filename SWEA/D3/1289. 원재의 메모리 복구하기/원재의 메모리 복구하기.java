import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
class Solution
{
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 1; i < t + 1; i++) {
			String inputS = br.readLine();
		
			int cnt = 0;
			char status = '0';
			
			for (int j = 0; j < inputS.length(); j++) {
				if (inputS.charAt(j) != status) {
					cnt++;
					status = (status == '0' ? '1' : '0');
				}
			}
			System.out.printf("#%d %d%n", i, cnt);
		}
	}
}