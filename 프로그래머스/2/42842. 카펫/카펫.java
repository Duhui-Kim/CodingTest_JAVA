class Solution {
    public int[] solution(int brown, int yellow) {
        
        for (int i = yellow; i>=1; i--) {
            if (yellow % i != 0) continue;
            
            int num1 = i + 2;
            int num2 = yellow / i + 2;                       
            
            if (num1 * 2 + num2 * 2 - 4 == brown) {
                return new int[] {num1, num2};
            }
        }
        
        return new int[2];
    }
}