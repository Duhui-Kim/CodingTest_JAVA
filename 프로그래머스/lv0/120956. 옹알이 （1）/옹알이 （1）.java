class Solution {
    public int solution(String[] babbling) {
        int[] newlist = new int[babbling.length];
		for(int i=0; i<babbling.length; i++) {
			babbling[i] = babbling[i].replace("aya", "1");
			babbling[i] = babbling[i].replace("ye", "1");
			babbling[i] = babbling[i].replace("woo", "1");
			babbling[i] = babbling[i].replace("ma", "1");
			for(char a: babbling[i].toCharArray()) {
				if(a == '1') {
					newlist[i] += 1;
				} else {
					newlist[i] = 0;
					break;
				}
			}
		}
		int answer = 0;
		for(int a : newlist) {
			if(a != 0) {
				answer += 1;
			}
		}
        return answer;
    }
}