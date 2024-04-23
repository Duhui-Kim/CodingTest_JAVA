import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerIdx = new HashMap<>();
        
        for (int i=0; i<players.length; i++) {
            playerIdx.put(players[i], i);
        }
        
        for (String call : callings) {
            int cur = playerIdx.get(call);
            playerIdx.replace(call, cur-1);
            playerIdx.replace(players[cur-1], cur);
            
            swap(players, cur-1, cur);
        }
                
        
        String[] answer = players;
        return answer;
    }
    
    private void swap(String[] arr, int i, int j) {
        String tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}