import java.util.*;
import java.io.*;

public class Solution {
	static int h, w;
	static char[][] map;
	static int dir; // 방향
	static int rr, cc;
	
	static char[] user = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			dir = 0; //  1 2 3 4 -> 상하좌우(UDLR)
			
			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					char start = line.charAt(j);
					map[i][j] = start;
					if (start == '^' || start == 'v' || start == '<' || start == '>') {
						dir = (start== '^' ? 1 :(start=='v' ? 2 : (start=='<'  ? 3 :(start=='>' ? 4 : 0))));
						rr = i; 
						cc = j;
					}
				}
			}
			
			
			int n = Integer.parseInt(br.readLine());
			String cmd = br.readLine();
			
			game(cmd, n);
			
			System.out.print("#" + t + " ");
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					System.out.print(map[i][j]);
					
				}
				System.out.println();
			}
		}
		
	}

	private static void game(String cmd, int cmdLen) {
		for (int idx = 0; idx < cmdLen; idx++) {
			// 분리
			char cmdChar = cmd.charAt(idx);
			// 명령어 구분
			int cmdNum = (cmdChar == 'S')? 5: (cmdChar=='U' ? 1 :(cmdChar=='D' ? 2 : (cmdChar=='L' ? 3 :(cmdChar=='R' ? 4 : 0))));
			//System.out.println(cmdChar + " : " + cmdNum);
			
			// 각 명령에 맞는 함수 호출
			if (cmdNum == 5) shoot(rr, cc);
			else move(rr, cc, cmdNum);			
		}
	}
	
	private static void move(int r, int c, int moveDir) { // 현재 방향은 중요X
		int nr = r, nc = c;
		
		switch (moveDir) {
		case 1:
			nr -= 1;
			break;
		case 2:
			nr += 1;
			break;
		case 3:
			nc -= 1;
			break;
		case 4:
			nc += 1;
			break;
		default:
			break;
		}
		
		// 갈 수 있는지 판단
		if (check(nr, nc)) {
			// 갈 수 있다면, (r, c)와 (nr, nc) 갱신
			map[r][c] = '.';
			map[nr][nc] = user[moveDir - 1];
			rr = nr;
			cc = nc;
		}
		else {
			map[r][c] = user[moveDir - 1];
		}
		dir = moveDir;
	}
	
	private static boolean check(int nr, int nc) {
		if (nr < 0 || nr >= h || nc < 0 || nc >= w) return false;
		else {
			char next = map[nr][nc];
			if (next == '-' || next == '#' || next == '*') return false;
		}
		return true;
	}
	
	
	private static void shoot(int r, int c) {
		int nr = r, nc = c;
		
		int offset = (dir == 1 || dir == 3)? -1 : 1;

		if (dir == 1 || dir == 2) {
			nr += offset;
		}
		else {
			nc += offset;
		}
		
		while (true) {
			if (nr < 0 || nr >= h || nc < 0 || nc >= w) break;
			
			char next = map[nr][nc];
			if (next == '#') { // '-' 처리
				break;
			}
			if (next == '*') {
				map[nr][nc] = '.';
				break;
			}
			
			if (dir == 1 || dir == 2) nr += offset;
			else nc += offset;
		}
	}
}
