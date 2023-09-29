class Solution {
    public int[] solution(String[] wallpaper) {
        int row = wallpaper.length;
        int col = wallpaper[0].length();
        
        int lux = 50;
        int luy = 50;
        int rdx = 0;
        int rdy = 0;
        
        for(int i=0; i<row; i++) {
            char[] nxt = wallpaper[i].toCharArray();
            
            for(int j=0; j<col; j++) {
                if(nxt[j] == '#') {
                    if(lux > i) {
                        lux = i;
                    }        
                    if(luy > j) {
                        luy = j;
                    }
                    
                    if(rdx < i+1) {
                        rdx = i+1;
                    }
                    if(rdy < j+1) {
                        rdy = j+1;
                    }
                }
            }
        }
        
        int[] answer = new int[]{lux, luy, rdx, rdy};
        return answer;
    }
}