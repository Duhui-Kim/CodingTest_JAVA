class Solution {
    public int solution(int n) {
        int i = 1;
        int answer = n;
		while(i<=answer) {
			if(i%3 == 0 || String.valueOf(i).contains("3")) {
				answer++;
			}		
			i++;
		}
        return answer;
    }
}