class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for (int i=0; i<balls.length; i++) {
            int x = balls[i][0];
            int y = balls[i][1];
            
            int tmpAns = 10_000_000;
            for (int j=0; j<4; j++) {
                int tmpX = x;
                int tmpY = y;
                
                switch(j) {
                    case(0) :
                        if (y == startY && x > startX) continue;
                        tmpX = m + m - x;
                        break;
                    case (1) :
                        if (y == startY && x < startX) continue;
                        tmpX = -x;
                        break;
                    case (2) :
                        if (x == startX && y > startY) continue;
                        tmpY = n + n - y;
                        break;
                    case (3) :
                        if (x == startX && y < startY) continue;
                        tmpY = -y;
                        break;
                }
                int distance = (startX - tmpX) * (startX - tmpX) + (startY - tmpY) * (startY - tmpY);
                
                tmpAns = tmpAns < distance ? tmpAns : distance;
            }
            
            answer[i] = tmpAns;
        }
        
        return answer;
    }
}