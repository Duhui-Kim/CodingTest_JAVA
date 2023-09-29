class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i=1; i<=n; i++) {
            if(n%i == 0) {
                if(n/i < i) {
                    break;
                } else if (n/i == i) {
                    answer += i;
                    break;
                } else {
                    answer += n/i + i;
                }
            }
        }
        return answer;
    }
}