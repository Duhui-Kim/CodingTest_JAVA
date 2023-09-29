import java.util.*;
import java.io.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> ans = new ArrayList<>();
        int idx = 0;
        ans.add(arr[0]);
        
        for(int i=1; i<arr.length; i++) {
            if(arr[i] == ans.get(idx)) continue;
            
            ans.add(arr[i]);
            idx++;
        }
        
        int[] answer = new int[ans.size()];
        
        for(int i=0; i<ans.size(); i++) {
            answer[i] = ans.get(i);
        }
        
        return answer;
    }
}