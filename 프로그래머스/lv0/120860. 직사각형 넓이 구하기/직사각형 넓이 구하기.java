class Solution {
    public int solution(int[][] dots) {
        int x = 0; int y = 0;
		
		for(int i=0; i<dots.length; i++) {
			for(int j=0; j<2; j++) {
				if(dots[0][0] != dots[i][0]) {
					x = Math.abs(dots[0][0] - dots[i][0]);
				} else if (dots[0][1] != dots[i][1]) {
					y = Math.abs(dots[0][1] - dots[i][1]);
				}
			}
		}

        int answer = x*y;

        return answer;
    }
}