import java.util.*;
import java.io.*;

public class Main {
    static int S, P;
    static int[] dna;
    static final int DNA_NUM = 4;

    static int[] current;
    static int[] target = new int[DNA_NUM];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dna = new int[S];

        String s = br.readLine();
        for (int i = 0; i < S; i++) {
            char temp = s.charAt(i);
            // A:0 C:1 G:2 T:3
            dna[i] = (temp == 'A')? 0 : (temp == 'C')? 1 : (temp == 'G')? 2 : 3;
        }

//        System.out.print(Arrays.toString(dna));

        // A:0 C:1 G:2 T:3
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < DNA_NUM; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }


        int left = 0;
        int right = P - 1;

        int count = 0;
        current = new int[DNA_NUM];
        for (int i = 0; i < P; i++) {
            current[dna[i]]++;
        }
//        System.out.println(Arrays.toString(current));

        while (true) {
            if (canMake()) {
                count++;
            }

            if (right == (S - 1)) break;

            current[dna[left]]--;
            left++;
            right++;
            current[dna[right]]++;
        }

        System.out.println(count);
    }

    private static boolean canMake() {
        for (int i = 0; i < DNA_NUM; i++) {
            if (current[i] < target[i]) {
                return false;
            }
        }
        return true;
    }
}