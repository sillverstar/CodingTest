import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken()); // 종이 가로 길이
        int h = Integer.parseInt(st.nextToken()); // 종이 세로 길이

        int c = Integer.parseInt(br.readLine());

        List<Integer> widthCuts = new ArrayList<>();
        List<Integer> heightCuts = new ArrayList<>();

        // 경계 추가
        widthCuts.add(0);
        widthCuts.add(w);
        heightCuts.add(0);
        heightCuts.add(h);

        for (int i = 0; i < c; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int pos = Integer.parseInt(st.nextToken());

            if (type == 0) {
                // 가로로 자름 -> 높이가 감소
                heightCuts.add(pos);
            } else {
                // 세로로 자름 -> 너비가 감소
                widthCuts.add(pos);
            }
        }

        // 정렬
        Collections.sort(widthCuts);
        Collections.sort(heightCuts);

        int maxWidth = 0;
        int maxHeight = 0;
        
        for (int i = 1; i < widthCuts.size(); i++) {
            maxWidth = Math.max(maxWidth, widthCuts.get(i) - widthCuts.get(i - 1));
        }
        
        for (int i = 1; i < heightCuts.size(); i++) {
            maxHeight = Math.max(maxHeight, heightCuts.get(i) - heightCuts.get(i - 1));
        }

        System.out.println(maxWidth * maxHeight);
    }
}