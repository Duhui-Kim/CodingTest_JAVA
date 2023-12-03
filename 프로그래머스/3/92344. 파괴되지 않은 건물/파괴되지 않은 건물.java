import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;
        
        int[][] array = new int[r][c];
        
        for(int[] check : skill) {
            if(check[0] == 1) {
                check[5] = -check[5];
            }
                        
            array[check[1]][check[2]] += check[5];
            
            if(check[4] + 1 < c) {
                array[check[1]][check[4]+1] += -check[5];
            }
            if(check[3] + 1 < r) {
                array[check[3]+1][check[2]] += -check[5];
            }
            if((check[3] + 1 < r) && (check[4] + 1 < c)) {
                array[check[3]+1][check[4]+1] += check[5];
            }
        }
        
        makeArray(array, r, c);
        
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                board[i][j] += array[i][j];
            }
        }
        
        answer = checkCount(board, r, c);
        
        return answer;
    }
    
    private void makeArray(int[][] array, int r, int c) {
        for(int i=1; i<r; i++) {
            for(int j=0; j<c; j++) {
                array[i][j] += array[i-1][j];
            }
        }
        
        for(int i=0; i<r; i++) {
            for(int j=1; j<c; j++) {
                array[i][j] += array[i][j-1];
            }
        }
    }
    
    private int checkCount(int[][] board, int r, int c) {
        int count = 0;
        
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(board[i][j] <= 0) continue;
                
                count++;
            }
        }
        
        return count;
    }
}