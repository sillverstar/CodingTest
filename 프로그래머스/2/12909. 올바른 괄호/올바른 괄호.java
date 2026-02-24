import java.util.Deque;
import java.util.ArrayDeque;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            
            if (temp == '(') {
                stack.push(temp);
            }
            else {
                if (stack.isEmpty())  {// 스택 안에 아무것도 없으면 false
                    return false;
                }
                stack.pop(); // 있으면 pop
            }
        }
        
        answer = (stack.isEmpty())? true : false;
        return answer;
    }
}