class Solution {
    public int[] solution(int[] numlist, int n) {
        int space = 0;
		for(int i=0; i<numlist.length; i++) {
			for(int j=0; j<numlist.length; j++) {
				if(Math.abs(n-numlist[i]) < Math.abs(n-numlist[j])) {
					space = numlist[i];
					numlist[i] = numlist[j];
					numlist[j] = space;
				} else if (Math.abs(n-numlist[i]) == Math.abs(n-numlist[j]) && numlist[i]>numlist[j]){
					space = numlist[i];
					numlist[i] = numlist[j];
					numlist[j] = space;
				}
			}
		}
        return numlist;
    }
}