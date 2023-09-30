class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        
        for(int cur : arr) {
            answer += cur;
        }
        
        return answer / (double) arr.length;
    }
}