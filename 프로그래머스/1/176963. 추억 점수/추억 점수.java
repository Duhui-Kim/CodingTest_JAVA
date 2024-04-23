import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> missingPoints = new HashMap<>();
        
        for (int i=0; i<name.length; i++) {
            missingPoints.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        
        for (int i=0; i<photo.length; i++) {
            for (String p : photo[i]) {
                if (missingPoints.containsKey(p)) {
                    answer[i] += missingPoints.get(p);
                }
            }
        }
        
        return answer;
    }
}