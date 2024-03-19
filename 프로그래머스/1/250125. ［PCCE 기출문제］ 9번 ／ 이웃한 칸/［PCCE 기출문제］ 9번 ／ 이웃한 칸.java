class Solution {
    private int[] dx = new int[] {0, 0, -1, 1};
    private int[] dy = new int[] {-1, 1, 0, 0};
    
    public int solution(String[][] board, int h, int w) {
        int answer = 0;
        
        for(int i=0; i<4; i++) {
            int nx = h + dx[i];
            int ny = w + dy[i];
            
            if(nx < 0 || ny < 0 || nx >= board.length || ny >= board.length) {
                continue;
            }
            
            if(board[h][w].equals(board[nx][ny])) {
                answer++;
            }
        }
        
        return answer;
    }
}