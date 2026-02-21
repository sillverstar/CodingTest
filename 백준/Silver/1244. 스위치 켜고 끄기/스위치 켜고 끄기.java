import java.io.*;
import java.util.*;

public class Main {
	static int n, s;
	static boolean[] switches;
	static Deque<int[]> students;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		n = Integer.parseInt(br.readLine());
		switches = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			switches[i] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
		}
		
		s = Integer.parseInt(br.readLine());
		students = new ArrayDeque<>();
		
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			students.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		
		while (!students.isEmpty()) {
			int[] cur = students.poll();
			int gender = cur[0];
			int num = cur[1] - 1; // idx로 바꿔줌
			
			if (gender == 1) { // 남학생
				toggle1(num);
			}
			else { // 여학생
				toggle2(num, num-1, num+1);
			}
		}
		
		printAns();
	}

	private static void toggle1(int num) {
		for (int i = num; i < n; i += num+1) { // 초기값은 idx, 더하는 값은 기존 num
			switches[i] = !switches[i];
		}
	}

	private static void toggle2(int num, int minus, int plus) {
		// 종료 조건
		if (minus < 0 || plus >= n) {
			switches[num] = !switches[num];
			return;
		}
		if (switches[minus] != switches[plus]) {
            switches[num] = !switches[num];
			return;
		}
		
		switches[minus] = !switches[minus];
		switches[plus] = !switches[plus];
		
		toggle2(num, minus-1, plus+1);
	}
	
	
	private static void printAns() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++) {
			sb.append((switches[i-1])?1:0).append(' ');
			if (i > 0 && i % 20 == 0) {
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}
}
