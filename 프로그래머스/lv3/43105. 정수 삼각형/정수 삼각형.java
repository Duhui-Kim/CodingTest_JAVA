class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] map = new int[n][n];
        
        map[0][0] = triangle[0][0];
        
        for(int i=0; i<n-1; i++) {
            for(int j=0; j<triangle[i].length; j++) {
                map[i+1][j] = Math.max(map[i+1][j], map[i][j] + triangle[i+1][j]);
                map[i+1][j+1] = Math.max(map[i+1][j+1], map[i][j] + triangle[i+1][j+1]);
            }
        }
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            answer = Math.max(answer, map[n-1][i]);
        }
        
        return answer;
    }
}