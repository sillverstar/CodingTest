import java.io.*;
import java.util.*;

public class Solution {
	static int n, maxCore, minLine;
	static int[][] map;
	
	static List<int[]> coreList;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력 & static 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());
			
			maxCore = 0;
			minLine = Integer.MAX_VALUE;
			map = new int[n][n];
			
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 입력 map 저장 확인
//			for (int i = 0; i < n; i++) {
//				System.out.println(Arrays.toString(map[i]));
//			}
			
			makeCoreList();
			
			// coreList 저장 확인
//			for (int[] c : coreList) {
//				System.out.println(Arrays.toString(c));
			
			dfs(0, 0, 0); //인덱스(코어리스트 탐색 예정), 연결된 코어 개수, 전선 길이
			System.out.println("#" + t + " " + minLine);
			
		}
		
	}
	
	private static void dfs(int idx, int coreCnt, int lineLength) {
		// 종료 조건
		if (idx == coreList.size()) {
			if (coreCnt > maxCore) {
				maxCore = coreCnt;
				minLine = lineLength;
			}
			else if (coreCnt == maxCore) {
				minLine = Math.min(minLine, lineLength);
			}
			return;
		}
	
		// 가지치기
		// 남은 코어를 모두 연결해도 maxCore를 넘지 못할 때
		int remain = coreList.size() - idx;
		if (coreCnt + remain < maxCore) return;
		
		int[] current = coreList.get(idx);
		int r = current[0];
		int c = current[1];
	
		// 전선 연결 O (상하좌우 확인)
		boolean connect = false;
		for (int d = 0; d < 4; d++) {
	        if (!canConnect(r, c, d)) continue;

	        connect = true;
	        
	        int added = setLine(r, c, d, 2);   // 전선 깔기 (길이 반환)
	        dfs(idx + 1, coreCnt + 1, lineLength + added); // 길이 추가
	        setLine(r, c, d, 0);          	
		}
		
		// 전선 연결 X
		dfs(idx + 1, coreCnt, lineLength);
	}
	
	private static boolean canConnect(int r, int c, int d) {
	    int nr = r + dr[d];
	    int nc = c + dc[d];

	    while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
	        if (map[nr][nc] != 0) return false; // 코어(1) 또는 전선(2)이면 막힘
	        nr += dr[d];
	        nc += dc[d];
	    }
	    return true;
	}

	private static int setLine(int r, int c, int d, int val) {
	    int nr = r + dr[d];
	    int nc = c + dc[d];
	    int len = 0;

	    while (nr >= 0 && nr < n && nc >= 0 && nc < n) {
	        map[nr][nc] = val; // 2로 깔기 / 0으로 원복
	        len++;
	        nr += dr[d];
	        nc += dc[d];
	    }
	    return len;
	}


	private static void makeCoreList() {
		coreList = new ArrayList<>();
		for (int i = 1; i < n-1; i++) { // 가장자리 제거
			for (int j = 1; j < n-1; j++) {
				if (map[i][j] == 1) {
					coreList.add(new int[] {i, j});
				}
			}
		}
	}
}