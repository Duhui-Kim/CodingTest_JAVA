import java.util.*;
import java.io.*;

class Solution {
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    public class Pair {
        int x, y;
        int dir; // 가로이동은 0, 세로이동은 1
        
        public Pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        
        int[][][] cost = new int[2][n][m];
        Queue<Pair> queue = new LinkedList<>();
        
        for(int i=0; i<n; i++) {
            Arrays.fill(cost[0][i], Integer.MAX_VALUE / 2);
            Arrays.fill(cost[1][i], Integer.MAX_VALUE / 2);
        }
        cost[0][0][0] = 0;
        cost[1][0][0] = 0;
        
        queue.offer(new Pair(0, 0, 0));
        queue.offer(new Pair(0, 0, 1));
        
        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            
            for(int i=0; i<4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if(board[nx][ny] == 1) continue;
                
                // 가로 이동이었을 경우
                if(p.dir == 0) {
                    // 가로 이동
                    if(i <= 1) {
                        if(cost[0][p.x][p.y] + 100 >= cost[0][nx][ny]) continue;
                        
                        cost[0][nx][ny] = cost[0][p.x][p.y] + 100;
                        queue.offer(new Pair(nx, ny, 0));
                    } 
                    // 세로 이동
                    else {
                        if(cost[0][p.x][p.y] + 600 >= cost[1][nx][ny]) continue;
                        
                        cost[1][nx][ny] = cost[0][p.x][p.y] + 600;
                        queue.offer(new Pair(nx, ny, 1));
                    }
                } 
                // 세로 이동이었을 경우
                else {
                    // 세로 이동
                    if(i > 1) {
                        if(cost[1][p.x][p.y] + 100 > cost[1][nx][ny]) continue;
                        
                        cost[1][nx][ny] = cost[1][p.x][p.y] + 100;
                        queue.offer(new Pair(nx, ny, 1));
                    } 
                    // 가로 이동
                    else {
                        if(cost[1][p.x][p.y] + 600 > cost[0][nx][ny]) continue;
                        
                        cost[0][nx][ny] = cost[1][p.x][p.y] + 600;
                        queue.offer(new Pair(nx, ny, 0));
                    }
                }
            }
        }
        return Math.min(cost[0][n-1][m-1], cost[1][n-1][m-1]);
    }
}