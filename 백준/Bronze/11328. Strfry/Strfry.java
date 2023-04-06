import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        loop:
        for(int tc=0; tc<N; tc++) {
            String a = sc.next();
            String b = sc.next();

            char[] aa = a.toCharArray();
            char[] bb = b.toCharArray();

            if(aa.length != bb.length) {
                System.out.println("Impossible");
                continue;
            }

            int[] arr = new int[30];
            for(int i=0; i<aa.length; i++) {
                arr[aa[i] - 'a']++;
            }
            for(int i=0; i<bb.length; i++) {
                arr[bb[i] - 'a']--;
                if(arr[bb[i] - 'a'] < 0) {
                    System.out.println("Impossible");
                    continue loop;
                }
            }
            System.out.println("Possible");
        }
    }
}