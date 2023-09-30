class Solution {
    int[] dx = {1, 0, -1};
    int[] dy = {0, 1, -1};
    
    public int[] solution(int n) {
        int[][] map = new int[n][n];
        int maxNum = (n + 1) * n / 2 + 1;
        
        int num = 1;
        int x = 0;
        int y = 0;
        while(num <= maxNum) {
            // 아래로 이동
            while(x < n && map[x][y] == 0) {
                map[x][y] = num;
                x += dx[0];
                y += dy[0];
                num++;
            }
            if(num == maxNum) break;
            
            x -= dx[0];
            y += dy[1];
            
            // 오른쪽으로 이동
            while(y < n && map[x][y] == 0) {
                map[x][y] = num;
                x += dx[1];
                y += dy[1];
                num++;
            }
            if(num == maxNum) break;
            
            y -= dy[1];
            x += dx[2];
            y += dy[2];
            
            while(map[x][y] == 0) {
                map[x][y] = num;
                x += dx[2];
                y += dy[2];
                num++;
            }
            if(num == maxNum) break;
            x -= dx[2];
            y -= dy[2];
            x += dx[0];
        }
        
        int[] answer = new int[maxNum-1];
        
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(map[i][j] != 0) {
                    answer[idx++] = map[i][j];
                }
            }
        }
        
        
        return answer;
    }
}