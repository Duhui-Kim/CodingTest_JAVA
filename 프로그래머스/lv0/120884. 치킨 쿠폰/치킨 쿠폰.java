class Solution {
    public int solution(int chicken) {
		int coupon = 0;
		while(chicken/10 > 0) {
			coupon += chicken/10;
			chicken = chicken%10 + chicken/10;
		}
        return coupon;
    }
}