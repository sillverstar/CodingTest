import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// SWEA 2115 벌꿀 채취

/*
 * - 연속된 M개의 벌통을 선택
 * 	- 두 일꾼이 겹치지 않게[조합 문제: 일꾼 두 명의 벌통 위치가 달라도 결과(수익)는 같음]
 * 	- 한 명이 2개 채취하는 거랑 같음(순서X)
 * 
 * - C를 초과하지 않는 양만큼 용기에 담기(부분집합의 합)
 * 	- 최대가 되는 경우
 */

public class Solution {
	
	static int n, m, c;
	static int[][] map, maxMap; // 입력받은 벌통의 상태map, 각 위치에서 연속된 m개의 벌통 중 최대이익 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			map = new int[n][n];
			maxMap = new int[n][n-m+1];
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#" + t + " " + getMaxBenefit());
		}
		
	}
	private static int getMaxBenefit() {
		// 연속된  m개를 선택할 수 있는 모든 행의 열이 위치마다 최대이익 계산
		makeMaxMap();
		// 두 일꾼의 선택 조합
		return processCombination();
	}
	
	private static void makeMaxMap() { //maxMap = new int[n][n-m+1];
		// TODO Auto-generated method stub
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n-m; j++) {
				// i, j 위치에서 부분집합을 따져보고 최대이익 계산
				//부분집합
				subset(i, j, 0, 0, 0);
			}
		}
	}
	
	
	private static void subset(int i, int j, int cnt, int sum, int powSum) { // 파라미터: 행, 열, 요소 개수, 합, ?
		// 가지치기
		if (sum > c) return;
		
		// 기저조건
		if (cnt == m) { // cnt == m && sum <= c
			if (maxMap[i][j-m] < powSum) maxMap[i][j-m] = powSum;
			return;
		}
		// i, j 벌통을 포함
		subset(i, j + 1, cnt + 1, sum + map[i][j], powSum + map[i][j]*map[i][j]);
		// i, j 벌통을 포함 X
		subset(i, j + 1, cnt + 1, sum, powSum);
 	}
	
	private static int processCombination() {
		
		int aBenefit, bBenefit, max = 0;
		// 일꾼 A 선택
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= n-m; j++) {
				aBenefit = maxMap[i][j];
			
				// 일꾼 B 선택
				bBenefit = 0;
				for (int i2 = i; i2 < n; i2++) {
					int start = (i == i2) ? j + m : 0;
					for (int j2 = start; j2 <= n-m; j2++) {
						if (bBenefit < maxMap[i2][j2]) {
							bBenefit = maxMap[i2][j2];
						}
					}
				}
				
				if (max < aBenefit + bBenefit) {
					max = aBenefit + bBenefit;
				}	
			}
		}
		return max;
	}
}
