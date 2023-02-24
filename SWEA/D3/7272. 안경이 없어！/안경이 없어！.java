import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        
        // 테스트케이스를 입력받고 진행
        for (int t = 1; t <= testCase; t++) {
            String a = sc.next();
            String b = sc.next();
            
            // 문자열이 같은지 다른지 확인하기 위해 isSame 변수를 선언
            boolean isSame = true;
            
            // a의 길이와 b의 길이다 다르면 다른 단어
            if(a.length() != b.length()) {
                isSame = false;
                
            // 길이가 같을 경우 아래 진행
            } else {
                loop:
                for (int i = 0; i < a.length(); i++) {
                    switch (a.charAt(i)) {
                        // a의 첫 번째 문자가 B일 때 b의 문자가 B인지 체크
                        case 'B':
                            if (b.charAt(i) != 'B') {
                                isSame = false;
                                break loop;
                            }
                            break;
                            
                        // a의 첫 번째 문자가 구멍하나 뚫린 문자일 경우 구멍 하나짜리인지 체크
                        case 'A': case 'D': case 'O': case 'P': case 'Q': case 'R':
                            if (b.charAt(i) != 'A' && b.charAt(i) != 'D' && b.charAt(i) != 'O' &&
                                    b.charAt(i) != 'P' && b.charAt(i) != 'Q' && b.charAt(i) != 'R') {
                                isSame = false;
                                break loop;
                            }
                            break;

                        // 아닐 경우 b의 첫 번째 문자가 구멍 두 개 또는 하나 뚫려있는 문자가 아닌지 체크한다.
                        default:
                            if (b.charAt(i) == 'B' || b.charAt(i) == 'A' || b.charAt(i) == 'D' || b.charAt(i) == 'O' ||
                                    b.charAt(i) == 'P' || b.charAt(i) == 'Q' || b.charAt(i) == 'R') {
                                isSame = false;
                                break loop;
                            }
                            break;
                    }
                }
            }
            // isSame boolean 변수에 따라 값을 출력한다.
            if(isSame) System.out.printf("#%d %s\n", t, "SAME");
            else System.out.printf("#%d %s\n", t, "DIFF");
        }
    }
}
