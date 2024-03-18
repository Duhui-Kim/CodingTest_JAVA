import java.util.*;

class Solution {
    private int[] dx = new int[] {-1, 1, 0, 0};
    private int[] dy = new int[] {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        int n = land.length;
        int m = land[0].length;
        int idx = 1;
        
        int[] sumOfOil = new int[m];
        Queue<int[]> queue = new LinkedList<>();
        
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(land[i][j] != 1) {
                    continue;
                }
                
                idx++;
                int minM = j;
                int maxM = j;
                int count = 0;
                
                land[i][j] = idx;
                queue.add(new int[] {i, j});
                count++;
                
                while(!queue.isEmpty()) {
                    int[] move = queue.poll();
                    
                    for(int k=0; k<4; k++) {
                        int nx = move[0] + dx[k];
                        int ny = move[1] + dy[k];
                        
                        if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                        if(land[nx][ny] != 1) continue;
                        
                        land[nx][ny] = idx;
                        count++;
                        queue.add(new int[] {nx, ny});
                        
                        if(ny < minM) {
                            minM = ny;
                        }
                        if(ny > maxM) {
                            maxM = ny;
                        }
                    }
                }
                
                for(int k = minM; k <= maxM; k++) {
                    sumOfOil[k] += count;
                }
            }
        }
        int answer = 0;
        
        for(int oil : sumOfOil) {
            answer = answer < oil ? oil : answer;
        }
        
        return answer;
    }
}