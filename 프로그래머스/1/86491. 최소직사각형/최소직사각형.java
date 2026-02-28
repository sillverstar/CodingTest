import java.util.*;
class Solution {
    static int answer;
    public int solution(int[][] sizes) {
        answer = 0;
        
        int maxW = 0;
        int maxH = 0;
        for (int[] size: sizes) {
            int width = 0, height = 0;
            if (size[0] > size[1]) {
                width = size[0];
                height = size[1];
            } else {
                width = size[1];
                height = size[0];
            }
            
            maxW = Math.max(maxW, width);
            maxH = Math.max(maxH, height);
        }
        
        // minSize;
        return maxW*maxH;
    }
    
}