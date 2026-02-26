import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int c = 0; c < commands.length; c++) {
            int[] cur = commands[c];
            int start = cur[0]-1;
            int len = cur[1];
            int num = cur[2]-1;
            
            List<Integer> temp = new ArrayList<>();
            for (int i = start; i < len; i++) {
                temp.add(array[i]);
            }
            Collections.sort(temp);
            
            answer[c] = temp.get(num);
        }
        return answer;
    }
}