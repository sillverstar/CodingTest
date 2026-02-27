import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        
        String[] temp = new String[numbers.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = String.valueOf(numbers[i]); // 문자열 배열로 바꾸기
        }
        
        Arrays.sort(temp, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        // 첫 번째 수가 0 = 이후 전부 0
        if (temp[0].equals("0")) {
            return "0";
        }
        
        // 반환 문자열 만들기
        for (int i = 0; i < temp.length; i++) {
            answer.append(temp[i]);
        }
        
        return answer.toString();
    }
}