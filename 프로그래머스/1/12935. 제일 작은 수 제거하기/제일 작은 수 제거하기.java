class Solution {
    public int[] solution(int[] arr) {
        int[] answer = new int[arr.length - 1];
        
        if(answer.length == 0) {
            return new int[] {-1};
        }
        
        int minNum = Integer.MAX_VALUE;
        for(int i=0; i< arr.length; i++) {
            if (minNum > arr[i]) {
                minNum = arr[i];
            }
        }
        
        for(int i=0, j=0; i<arr.length; i++) {
            if(arr[i] == minNum) continue;
            
            answer[j++] = arr[i];
        }
        
        return answer;
    }
}