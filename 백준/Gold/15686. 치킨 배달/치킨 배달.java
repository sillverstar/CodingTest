import java.util.*;
import java.io.*;

// 조합으로 치킨집 선택
// 최솟값 계산 후 갱신 return
class Pos {
	int x;
	int y;
	
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + this.x + " " + this.y + ")";
	}
}


public class Main {
	static int n, m;
	static int[][] city;
	static List<Pos> homes;
	static List<Pos> stores;
	
	static List<Pos> selected;
	static int answer;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		city = new int[n][n];
		homes = new ArrayList<>();
		stores = new ArrayList<>();
		selected = new ArrayList<>();
		
		// 도시 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				
				if (city[i][j] == 1) {
					homes.add(new Pos(i, j));
				} else if (city[i][j] == 2) {
					stores.add(new Pos(i, j));
				}
			}
		}
		
//		System.out.println(homes);
//		System.out.println(stores);
		
		answer = Integer.MAX_VALUE;
		
		comb(0, 0);
		System.out.println(answer);
		
	}

	private static void comb(int cnt, int start) {
		// 종료 조건
		if (cnt == m) {
			int sum = 0;
			
			for (Pos home : homes) {
				int minDist = Integer.MAX_VALUE;
				
				// 하나의 집에서 가장 짧은 치킨 거리 구하기
				for (Pos store : selected) {
					int dist = Math.abs(home.x - store.x) + Math.abs(home.y - store.y);
					minDist = Math.min(minDist, dist);
				}
				
				sum += minDist;
			}
			
//			기존 값이랑 현재 계산한 값 중에 더 짧은 최솟값으로 갱신
			answer = Math.min(answer, sum);
			return;
		}
		
		// stores 중에 m개를 고르기.
		for (int i = start; i < stores.size(); i++) {
			selected.add(stores.get(i));
			comb(cnt + 1, i + 1);
			selected.remove(selected.size() - 1); // 마지막에 넣었던 값 빼기
			
		}
	}


}
