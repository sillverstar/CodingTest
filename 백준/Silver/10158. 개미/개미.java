import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로(w) x 세로(h)
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // 초기 위치 (p, q)
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        int xRaw = (p + k) % (2 * w);
        int yRaw = (q + k) % (2 * h);

        int x = (xRaw <= w) ? xRaw : (2 * w - xRaw);
        int y = (yRaw <= h) ? yRaw : (2 * h - yRaw);

        System.out.printf("%d %d\n", x, y);
    }
}