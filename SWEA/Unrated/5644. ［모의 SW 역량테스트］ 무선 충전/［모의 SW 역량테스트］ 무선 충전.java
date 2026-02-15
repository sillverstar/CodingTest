import java.io.*;
import java.util.*;

public class Solution {
	static final int MAP_SIZE = 10;
	static int[][] map;
	static int m, a, maxCharge;
	static int[] userA, userB, apPer;
	
	static int[] dr = {0, -1, 0, 1, 0}; // 제자리, 상, 우, 하, 좌 순서로 수정
	static int[] dc = {0, 0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			map = new int[MAP_SIZE][MAP_SIZE];
			
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			maxCharge = 0;
			
			userA = new int[m + 1];
			userB = new int[m + 1];
			apPer = new int[a];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				userA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) {
				userB[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				makeMap(x, y, c, i);	
				// 성능은 따로 배열에 저장해준다.
				apPer[i] = Integer.parseInt(st.nextToken());
				
			}
			
			move();
			sb.append('#').append(t).append(' ').append(maxCharge).append('\n');
			
//			for (int i = 0; i < map.length; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
		}
		System.out.println(sb);
	}

	private static void move() {
		// 초기 위치 확인
		int ar = 0, ac = 0;
		int br = 9, bc = 9;
		
		calculateCharge(ar, ac, br, bc);
		
		// 이후 위치 학인
		for (int i = 0; i < m; i++) {
			// userA에서 방향을 가져와서 이동정보에 맞게 이동
			int dirA = userA[i];
			ar += dr[dirA];
			ac += dc[dirA];
			
			// userB에서 방향을 가져와서 이동정보에 맞게 이동
			int dirB = userB[i];
			br += dr[dirB];
			bc += dc[dirB];
			
			calculateCharge(ar, ac, br, bc);
		}
	}

	
	private static void calculateCharge(int ar, int ac, int br, int bc) {
		int aMask = map[ar][ac];
		int bMask = map[br][bc];
		
		// aMask = 0, bMask = 0
		if (aMask == 0 && bMask == 0) return;
		
		// aMask = 0 -> bMask만 계산
	    if (aMask == 0) {
	    	calculateOne(bMask);
	    	return;
	    }

	    // bMask = 0 -> aMask만 계산
	    if (bMask == 0) {
	    	calculateOne(aMask);
	    	return;
	    }
	    
	    
	    int charge = 0;
		
		// 두 개 다 AP가 있을 때 -> AC 개수인 a만큼 돌면서 가능한 경우, 계산하기.
	    for (int i = 0; i < a; i++) {
	        if ((aMask & (1 << i)) == 0) continue; // i번 AP 범위에 a가 없으면 건너뛰기.

	        for (int j = 0; j < a; j++) {
	            if ((bMask & (1 << j)) == 0) continue; // i번 AP 범위에 b가 없으면 건너뛰기.
	            
	            int temp = 0;
	            
	            if (i == j) { // 같은 AP -> 나눠쓰기
	            	temp = apPer[i];
	            }
	            else { // 다른 AP -> 각각 더하기
	            	temp = apPer[i] + apPer[j];
	            }
	            
	            charge = Math.max(charge, temp);
	        }
	    }
	    maxCharge += charge;
	}
	
	// 하나만 있을 때 함수
	private static void calculateOne(int mask) {
	    int charge = 0;
	    for (int i = 0; i < a; i++) {
	        if ((mask & (1 << i)) != 0) {
	            charge = Math.max(charge, apPer[i]);
	        }
	    }
	    maxCharge += charge;
	}

	
	// 충전 범위를 반영한 map 만들기
	private static void makeMap(int x, int y, int c, int idx) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				// 맨해튼 거리 (행, 열이 반대)
				int dist = Math.abs(i - y) + Math.abs(j - x);
				
				if (dist <= c) { // 거리가 커버리지보다 작거나 같으면
					map[i][j] |= 1 << idx; // 인덱스 위치에 표시를 해준다.
				}
			}
		}
	}
}
