import java.util.*;
import java.io.*;

public class Main {
	static int n, maxCnt;
	static Meeting[] meetings;
	// 클래스 
	static class Meeting implements Comparable<Meeting>{
		// implements Comparable<T> : 내가 만든 Meeting 객체끼리 서로 어떻게 비교할지 내가 직접 정의해볼게!
		// 기본 타입(int, double)은 값을 기준으로 비교하지만, 객체의 경우 어떻게 비교할지 정의해줘야 함
		int start, end; // 시작 시간, 종료 시간

		// 생성자
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Meeting o) {
			// 종료시간이 빠른 순
			int diff = Integer.compare(this.end, o.end);
			if (diff != 0) return diff;
			 
			// 종료 시간이 같다면 시작 시간이 빠른 순
			return Integer.compare(this.start, o.start);
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		n = Integer.parseInt(br.readLine());
		maxCnt = 0;
		
		meetings = new Meeting[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		getSchedule();
		System.out.println(maxCnt);
	}
	
	private static void getSchedule() {
		Arrays.sort(meetings);

		int prev = 0;
		
		for (Meeting m : meetings) {
			if (prev <= m.start) {
				maxCnt++;
				prev = m.end;
			}
		}
	}
}
