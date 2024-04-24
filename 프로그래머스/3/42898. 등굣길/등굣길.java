class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[m+1][n+1];
        map[1][1] = 1;
        
        for (int[] puddle : puddles) {
            map[puddle[0]][puddle[1]] = -1;
        }
        
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                if (map[i][j] < 0) continue;
                
                long sum = map[i][j];
                sum += map[i-1][j] < 0 ? 0 : map[i-1][j];
                sum += map[i][j-1] < 0 ? 0 : map[i][j-1];
                
                map[i][j] = (int) sum % 1000_000_007;
            }
        }
        
        return map[m][n];
    }
}