class Solution {
    public int solution(int n) {
        int[] arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = (i + 1) * i / 2;
        }

        int answer = 0;

        for (int i=n; i>0; i--) {
            for (int j=i-1; j>=0; j--) {
                if (arr[i] - arr[j] == n) {
                    answer++;
                    break;
                } else if (arr[i] - arr[j] > n || arr[i] < n) {
                    break;
                }
            }
        }
        
        return answer;
    }
}