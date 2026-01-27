import java.util.*;

public class Solution {
    public Stack<Integer> solution(int []arr) {
        Stack<Integer> st = new Stack<>();
        st.push(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            if (st.peek() == arr[i]) {
                continue;
            }
            else {
                st.push(arr[i]);
            }
        }
        return st;
    }
}