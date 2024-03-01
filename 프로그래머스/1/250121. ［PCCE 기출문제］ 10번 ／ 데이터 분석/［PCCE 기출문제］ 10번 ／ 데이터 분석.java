import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int ext_idx = getIdx(ext);
        int sort_idx = getIdx(sort_by);
        
        int cnt = 0;
        for(int i=0; i<data.length; i++) {
            if(data[i][ext_idx] >= val_ext) {
                cnt++;
                data[i] = null;
            }
        }
        
        int[][] answer = new int[data.length - cnt][4];
        
        for(int i=0, j=0; i<data.length; i++) {
            if(data[i] == null) {
                continue;
            }
            answer[j++] = data[i];
        }
        
        Arrays.sort(answer, (o1, o2) -> o1[sort_idx] - o2[sort_idx]);
        
        return answer;
    }
    
    private int getIdx(String column) {
        switch(column) {
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            case "remain":
                return 3;
        }
        return 0;
    }
}