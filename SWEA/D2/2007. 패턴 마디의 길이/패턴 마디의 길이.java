import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        // 테스트 케이스만큼 반복을 진행한다.
        for(int t=1; t<=testCase; t++) {
            // String을 입력받는다.
            String input = sc.next();

            // 값을 반환할 idx와 일치여부를 확인할 find 함수를 정의한다.
            int idx = 0;
            boolean find = false;

            // find가 false일 경우에 계속해서 진행
            while(!find) {
                // idx는 처음에 0이었지만, 0번째 글자와 같은 다음 글자를 찾아 idx에 저장한다.
                idx = findIdx(input, idx, input.length());

                // find를 true로 설정하고 값을 비교한다.
                find = true;
                for(int i=0; i<idx; i++) {
                    // 단어가 같지 않으면 find를 false로 변경하고 다시 위로 돌아가 다음 idx를 찾는다.
                    if(input.charAt(i) != input.charAt(idx+i)) {
                        find = false;
                        break;
                    }
                }
                // idx만큼 증가시키며 값이 일치하는지 확인한다.
                // ex) cocoa 의 경우 이 작업을 안하면 2개로 인식되지만, 사실 5개가 반복된다.
                //     cocoacoco 의 경우도 9개인데 2개로 나올 수 있다.
                for (int i = 0; i < idx; i++) {
                    for (int j = i+idx; j < input.length(); j+=idx) {
                        if(input.charAt(i) != input.charAt(j)) {
                            find = false;
                            break;
                        }
                    }
                }
            }
            // 값이 같을 경우 해당 idx를 출력한다. (idx의 값 == 단어의 크기)
            System.out.printf("#%d %d\n", t, idx);
        }
    }

    // start+1부터 end 안에서 0번 idx와 같은 글자를 찾는 method
    private static int findIdx(String str, int start, int end) {
        for(int i=start+1; i<end; i++) {
            if(str.charAt(0) == str.charAt(i)) return i;
        }
        return 0;
    }
}