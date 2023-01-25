class Solution {
    public int[] solution(int num, int total) {
        int[] answer = new int[num];
		
		for(int i=-50; i<=total; i++) {
			int sum=0;
			for(int j=i; j<i+num; j++) {
				sum+=j;
			}
			if(sum==total) {
				for(int j=i, idx=0; j<i+num; j++) {
					answer[idx++] = j;
				}
				break;
			}
		}
        return answer;
    }
}