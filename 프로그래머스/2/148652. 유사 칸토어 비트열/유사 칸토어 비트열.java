class Solution {
    public int solution(int n, long l, long r) {       
        long maxLength = 1;
        long maxCount = 1;
        
        for(int i=0; i<n; i++) {
            maxLength *= 5;
            maxCount *= 4;
        }
        
        return (int) calculate(1, maxLength, l, r, maxCount);
    }
    
    private long calculate(long start, long end, long l, long r, long maxCount) {        
        if(l <= start && end <= r) {
            return maxCount;
        }
        
        if(end < l || start > r) {
            return 0;
        }
        
        long gap = (end - start + 1) / 5;
        long nextCount = maxCount / 4;
        
        return calculate(start + gap * 0, start + gap * 1 - 1, l, r, nextCount)
            + calculate(start + gap * 1, start + gap * 2 - 1, l, r, nextCount)
            + calculate(start + gap * 3, start + gap * 4 - 1, l, r, nextCount)
            + calculate(start + gap * 4, start + gap * 5 - 1, l, r, nextCount);
    }
}