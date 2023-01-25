class Solution {
    public int solution(int a, int b) {
        int g = 0;
		if(a>b) {
			for(int i=1; i<=b; i++) {
				if(a%i == 0 && b%i == 0) {
					g = i;
				}
			}
		} else {
			for(int i=1; i<=a; i++) {
				if(a%i == 0 && b%i == 0) {
					g = i;
				}
			}
		}
		b = b/g;
		while(b%2==0) b = b/2;
		while(b%5==0) b = b/5;
		
		if(b==1) {
			return 1;
		} else if(b%a == 0 && b/a == 1) {
			return 1;
		}
		return 2;
    }
}