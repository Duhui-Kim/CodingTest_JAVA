import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        int[] arr = new int[10_000_001];
        
        for (int tan : tangerine) {
            arr[tan]++;
        }
        
        Arrays.sort(arr);
        
        for (int i=10_000_000; i>=0; i--) {
            if (k <= 0) break;
            
            k -= arr[i];
            answer++;
        }
        
        return answer;
    }
}