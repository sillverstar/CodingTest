import java.util.*;
import java.io.*;

public class Main {
	static int n, maxCnt;
	static Meeting[] meetings;
	// 클래스 
	static class Meeting implements Comparable<Meeting>{
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

		maxCnt++;
		int prev = meetings[0].end;
		
		for (int i = 1; i < meetings.length; i++) {
			if (prev <= meetings[i].start) {
				maxCnt++;
				prev = meetings[i].end;
			}
		}
	}
}
