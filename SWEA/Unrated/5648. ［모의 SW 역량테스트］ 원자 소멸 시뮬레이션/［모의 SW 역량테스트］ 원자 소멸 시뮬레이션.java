import java.util.*;
import java.io.*;
/*
 * <좌표를 2배 하는 이유>
 * - 원자들이 0.5초 단위에 충돌할 수 있음.
 * - 좌표를 2배로 스케일링하면 0.5 단위가 정수 단위가 되어 충돌 시간을 정수로 계산할 수 있다.
 */



public class Solution {
	static int n;
	static Atom[] list;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			StringTokenizer st = null;
			
			list = new Atom[n];
			
			for (int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken()) * 2; // x
				int y = Integer.parseInt(st.nextToken()) * 2; // y
				int d = Integer.parseInt(st.nextToken()); // 방향
				int e = Integer.parseInt(st.nextToken()); // 에너지
				
				list[i] = new Atom(x, y, d, e);
			}
			System.out.println("#" + t + " " + makeBoomPair());
		}
	}
	
	// 모든 원자 쌍에 대해 충돌 가능한 경우인지 판별하고, 충돌한다면 (i, j, 충돌시간)을 Pair로 저장한다.
	private static int makeBoomPair() {
		Arrays.sort(list);
		
		ArrayList<Pair> boomPairs = new ArrayList<>();
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				Atom a = list[i], b = list[j];
				
				// 수직선에서 만날 때(x좌표가 같다)
				if (a.x == b.x) {
					if (a.dir == 0 && b.dir == 1) {
						boomPairs.add(new Pair(i, j, Math.abs(a.y - b.y)/ 2));
					}
				}
				
				// 수평선에서 만날 때(y좌표가 같다)
				if (a.y == b.y) {
					if (a.dir == 3 && b.dir == 2) {
						boomPairs.add(new Pair(i, j, Math.abs(a.x - b.x)/ 2));
					}
				}
				
				// 대각선(/) 라인에 있는 애들이 만날 때: 행과 열의 차이(-)가 일정
				if (a.x - a.y == b.x - b.y) {
					if (a.dir == 3 && b.dir == 1 || a.dir == 0 && b.dir == 2) {
						boomPairs.add(new Pair(i, j, Math.abs(a.x - b.x)));
					}
				}
				
				// 대각선(\) 라인에 있는 애들이 만날 때 행과 열의 합(+)이 일정
				if (a.x + a.y == b.x + b.y) {
					if (a.dir == 1 && b.dir == 2 || a.dir == 3 && b.dir == 0) {
						boomPairs.add(new Pair(i, j, Math.abs(a.x - b.x)));
					}
				}
			}
		}
		return getTotalEnergy(boomPairs);
	}
	
	
	// 충돌 이벤트들을 폭발시간 기준 오름차순으로 정렬한다.
	// -> 각 원자는 "가장 빠른 폭발 시간"이 정해지면 그 시간에만 에너지가 합산된다.
	private static int getTotalEnergy(ArrayList<Pair> boomPairs) {
		Collections.sort(boomPairs, (a, b) -> a.time <= b.time ? -1 : 1); // 원자조합쌍의 폭발시간 기준 오름차순 정렬
		
		final int INF = Integer.MAX_VALUE;
		
		int boomTimes[] = new int[n]; // 각 원자들이 폭발하는 가장 빠른 시간 기록
		Arrays.fill(boomTimes, INF); // 최대값으로 초기화
		
		int sum = 0;
		for (Pair p : boomPairs) {
			// 둘 중 하나라도 p.time보다 빨리 터졌으면 충돌 X. continue;
			if (boomTimes[p.i] < p.time || boomTimes[p.j] < p.time)
				continue;

			// i가 아직 안 터졌으면 p.time에 터지는 걸로 확정 & 에너지 더하기
			if (boomTimes[p.i] == INF) { // 안터졌다면
				boomTimes[p.i] = p.time;
				sum += list[p.i].e;
			}

			// j가 아직 안 터졌으면 p.time에 터지는 걸로 확정 & 에너지 더하기
			if (boomTimes[p.j] == INF) { // 안터졌다면
				boomTimes[p.j] = p.time;
				sum += list[p.j].e;
			}
		}
		return sum;
	}

	
	// 원자 정보: (x, y, dir, energy)
	static class Atom implements Comparable<Atom> {
		int x, y, dir, e;
		
		public Atom(int x, int y, int dir, int e) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.e = e;	
		}
		@Override
		public int compareTo(Atom o) {
			int diff = Integer.compare(this.x, o.x);
			return diff != 0 ? diff : Integer.compare(this.y, o.y);
		}
	}
	
	
	// 충돌 후보: (원자 i, 원자 j, 충돌 시간)
	static class Pair {
		int i, j;
		int time;

		public Pair(int i, int j, int time) {
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}
}
