import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        
        // 테스트케이스만큼 반복
        for (int t = 1; t <= testCase; t++) {
            // cnt는 0으로 잡고 str을 입력받는다.
            int cnt = 0;
            String str = sc.next();
            
            // pre에 첫번째 숫자를 넣는다.
            int pre = str.charAt(0) - '0';
            
            // 첫번째 숫자가 1이면 cnt를 증가시킨다.
            if(pre == 1) cnt++;
            
            // 1부터 끝까지 진행하며 숫자가 바뀌는 지점마다 cnt를 증가시킨다.
            for(int i=1; i<str.length(); i++){
                int post = str.charAt(i) - '0';
                if(pre != post) {
                    cnt++;
                    pre = post;
                }
            }   
            // cnt를 반환한다.
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
