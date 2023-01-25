class Solution {
    public int solution(int[] array) {
        int[] idxlist = new int[1001];
		int max = 0;
		for(int i=0; i<array.length; i++) {
			idxlist[array[i]]++;
		}
		int idxmax = 0; int idx = 0;
		for(int i=0; i<1001; i++) {
			if(idxlist[i] != 0 && idxmax < idxlist[i]) {
				idxmax = idxlist[i];
				idx = i;
			}
		}
		
		for(int i=0; i<1001; i++) {
			if(i != idx && idxmax == idxlist[i]) {
				return -1;
			}
		}
			
		return idx;
    }
}