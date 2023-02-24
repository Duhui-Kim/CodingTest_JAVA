import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            String a = sc.next();
            String b = sc.next();

            boolean isSame = true;
            loop:
            for (int i = 0; i < a.length(); i++) {
                if(a.length() != b.length()) {
                    isSame = false;
                    break loop;
                }
                
                switch (a.charAt(i)) {
                    case 'B':
                        if(b.charAt(i) != 'B'){
                            isSame = false;
                            break loop;
                        }
                        break;
                    case 'A': case 'D': case 'O': case 'P': case 'Q': case 'R':
                        if(b.charAt(i) != 'A' && b.charAt(i) != 'D' && b.charAt(i) != 'O' &&
                                b.charAt(i) != 'P' && b.charAt(i) != 'Q' && b.charAt(i) != 'R'){
                            isSame = false;
                            break loop;
                        }
                        break;

                    default:
                        if(b.charAt(i) == 'B' || b.charAt(i) == 'A' || b.charAt(i) == 'D' || b.charAt(i) == 'O' ||
                                b.charAt(i) == 'P' || b.charAt(i) == 'Q' || b.charAt(i) == 'R') {
                            isSame = false;
                            break loop;
                        }
                        break;
                }
            }
            if(isSame) System.out.printf("#%d %s\n", t, "SAME");
            else System.out.printf("#%d %s\n", t, "DIFF");
        }
    }
}
