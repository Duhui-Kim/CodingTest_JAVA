class Solution {
    public int solution(int n) {
        int bit = Integer.bitCount(n);
        
        while (bit != Integer.bitCount(++n)) {}
        
        return n;
    }
}