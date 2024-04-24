import java.util.*;

class Solution {
    public String solution(String s) {
        char[] answer = s.toLowerCase().toCharArray();
        
        boolean check = true;
        for (int i=0; i<answer.length; i++) {
            if (answer[i] == ' ') {
                check = true;
                continue;
            }
            
            if (check) {
                toUpperCase(answer, i);
                check = false;
            }
        }
        
        return String.valueOf(answer);
    }
    
    private void toUpperCase(char[] arr, int idx) {
        int num = (int) arr[idx];
                
        if (num < 58) {
            return;
        }
                
        arr[idx] = (char) (num - 32); 
    }
}