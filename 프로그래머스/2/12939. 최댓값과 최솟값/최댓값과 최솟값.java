import java.util.*;

class Solution {
    public String solution(String s) {
        StringTokenizer st = new StringTokenizer(s);
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());
            
            min = min > next ? next : min;
            max = max < next ? next : max;
        }        
        
        String answer = min + " " + max;
        return answer;
    }
}