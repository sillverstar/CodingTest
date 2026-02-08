import java.io.*;
import java.util.*;

public class Solution {
	static int n, m, c;
	static int[][] map; // 입력받은 map
	static int[][] maxMap; // M칸씩 구간별로 최대 이익을 계산한 map

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			// 입력 및 static 변수 초기화
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			maxMap = new int[n][n-m+1]; // 선택한 M 구간의 첫 칸에 기록
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + t + " " + getMaxBenefit()); // 최대 수익을 구하는 함수
		}
	}

	
// ================================================
	
	private static int getMaxBenefit() {
		// 1. 연속된 M칸씩 구간별로 최대 이익을 계산하여 maxMap 갱신
		makeMaxMap();
		
		// 2. maxMap에서 겹치지 않는 두 개의 조합을 골라서 최대 수익을 반환
		return processCombination();
	}
	
	
// ================================================

	// 1. 연속된 M칸씩 구간별로 최대 이익을 계산하여 maxMap 갱신
	private static void makeMaxMap() {
		// 1-1) 연속된 M칸의 구간 선택
		// 슬라이딩 윈도우: 연속된 구간을 정해놓고 그 구간을 한 칸씩 옮기면서 전부 살펴보는 방식)
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n-m+1; j++) { // 이 구간만 탐색 (M칸 유지는 subset 함수 내에서)
				// 1-2) 구간별 최대 이익을 maxMap에 저장
				subset(i, j, 0, 0, 0);	
			}
		}
	}

	// 1-2) 구간별 최대 이익을 maxMap에 저장
	private static void subset(int i, int j, int cnt, int sum, int powSum) {
		// <subset 파라미터>
		// i, j: 위치 | cnt: 현재 깊이 | sum: 조건 C와 비교하기 위한 구간합 | powSum: maxMap에 저장할 최대 제곱합
		
		// 가지치지
		if (sum > c) return; // 만약 합이 C를 넘으면 m까지 가지 않아도 종료(채취할 수 있는 양은 C를 넘을 수 없으므로)
		
		// 기저조건(== 종료 조건)
		if (cnt == m) { // M 구간의 요소들을 모두 탐색했다면 maxMap을 갱신
			if (maxMap[i][j-m] < powSum) { // 기존에 저장된 maxMap의 값보다 현재 계산한 powSum이 크다면 
				// maxMap 갱신
				maxMap[i][j-m] = powSum;		
			}
			return;
		}
		
		// 부분집합: 구간을 정하지 않고 각 "요소 관점"에서 넣을지 말지 결정하는 것
		// 현재 위치(i, j)의 벌통을 넣을 거에요.
		subset(i, j + 1, cnt + 1, sum + map[i][j], powSum + (int) Math.pow(map[i][j], 2));
		
		// 현재 위치(i, j)의 벌꿀을 넣지 않을 거에요.
		subset(i, j + 1, cnt + 1, sum, powSum);	
	}

	
	// 2. maxMap에서 겹치지 않는 두 개의 조합을 골라서 최대 수익을 반환
	private static int processCombination() {
		int aProfit, bProfit, maxProfit = 0;
		
		// maxMap[n][n-m+1] 탐색
		for (int ai = 0; ai < n; ai++) {
			for (int aj = 0; aj < n-m+1; aj++) {
				aProfit = maxMap[ai][aj];
				
				bProfit = 0;
				for (int bi = ai; bi < n; bi++) {
					int start = (ai == bi) ? aj + m : 0; // 행이 다르면 겹칠 수 없음
					for (int bj = start; bj < n-m+1; bj++) {
						if (bProfit < maxMap[bi][bj]) { // 가장 큰 것만
							bProfit = maxMap[bi][bj];
						}	
					}
				}
				if (maxProfit < aProfit + bProfit) {
					maxProfit = aProfit + bProfit;
				}
			}
		}
		return maxProfit;
	}
}
