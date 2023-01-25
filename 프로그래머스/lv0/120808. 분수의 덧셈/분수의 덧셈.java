class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = new int[2];
		int g = 0;

		answer[0] = numer1*denom2 + numer2*denom1;
		answer[1] = denom1*denom2;
		if(answer[0]>answer[1]) {
			for(int i=1; i<=answer[1]; i++) {
				if(answer[0]%i == 0 && answer[1]%i == 0) {
					g = i;
				}
			}
		} else {
			for(int i=1; i<=answer[0]; i++) {
				if(answer[0]%i == 0 && answer[1]%i == 0) {
					g = i;
				}
			}
		}
		
		answer[0] /= g;
		answer[1] /= g;
        return answer;
    }
}