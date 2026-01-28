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
			List<Integer> inputArr = new ArrayList<>();
		
			for (int j = 0; j <inputS.length(); j++) {
				inputArr.add(Character.getNumericValue(inputS.charAt(j)));
			}
			
			int cnt = 0;
			boolean status = false;
			
			for (int j = 0; j < inputArr.size(); j++) {
				if (inputArr.get(j) != (status ? 1 : 0)) {
					cnt++;
					status = !status;
				}
			}
			System.out.printf("#%d %d%n", i, cnt);
		}
	}
}