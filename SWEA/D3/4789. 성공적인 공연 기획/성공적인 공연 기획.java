import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        for (int t = 1; t <= testCase; t++) {
            String str = sc.next();
        
            // input을 저장할 배열과 누적합을 저장할 배열을 선언한다. 
            int[] arr = new int[str.length()];
            int[] sum = new int[str.length()];
            
            // input을 arr에 저장한다.
            for (int i = 0; i < str.length(); i++) {
                arr[i] = str.charAt(i) - '0';
            }
            
            // cnt는 0부터 시작하고, sum[0]에는 arr[0] 값을 넣는다.
            int cnt = 0;
            sum[0] = arr[0];
            
            // i는 1부터 진행하며 sum[i-1]과 비교하여 i가 더 클 경우
            // 사람 수를 더 해준다.
            for (int i = 1; i < str.length(); i++) {
                if(i > sum[i-1]) {
                    cnt += i - sum[i-1];
                    sum[i-1] += i - sum[i-1];
                }
                sum[i] = sum[i-1] + arr[i];
            }
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
