import java.util.*;

class Solution {
    boolean solution(String s) {       
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        char[] arr = s.toCharArray();
        for (int i=0; i<arr.length; i++) {
            if (arr[i] == '(') {
                stack.offer(arr[i]);
                continue;
            }
            
            if (!stack.isEmpty() && stack.peekLast() == '(') {
                stack.pollLast();
            } else {
                return false;
            }
        }
        
        if (stack.isEmpty()) {
            return true;
        }
        
        return false;
    }
}