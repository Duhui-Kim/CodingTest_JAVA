class Solution {
    public int solution(int[] nums) {
        boolean[] prime = new boolean[3001];
        
        prime[0] = true;
        prime[1] = true;
        
        for(int i=2; i<=60; i++) {
            if(prime[i]) continue;
            
            for(int j = i*i; j <= 3000; j += i) {
                prime[j] = true;
            }
        }
        
        int length = nums.length;
        int answer = 0;
        for(int i=0; i<length-2; i++) {
            for(int j=i+1; j<length-1; j++) {
                for(int k=j+1; k<length; k++) {
                    if(!prime[nums[i] + nums[j] + nums[k]])
                        answer++;
                }
            }
        }
        return answer;
    }
}