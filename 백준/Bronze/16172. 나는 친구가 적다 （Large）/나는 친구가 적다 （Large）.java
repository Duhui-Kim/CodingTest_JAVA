import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] a = sc.nextLine().toCharArray();
        char[] b = sc.nextLine().toCharArray();

        // 실패 배열 생성
        int[] failure = new int[b.length];

        // b 문자열에 대한 배열 채우기
        int j = 0;
        for (int i = 1; i < b.length; i++) {
            while (j > 0 && b[i] != b[j]) j = failure[j-1];
            if (b[i] == b[j]) failure[i] = ++j;
        }

        // 존재 여부를 저장할 boolean
        boolean exist = false;
        j = 0;
        
        // a 문자열과 b 문자열을 비교하는데 a 문자열이 숫자면 건너뜀
        for (int i = 0; i < a.length; i++) {
            if (a[i] >= '0' && a[i] <= '9') continue;

            while (j > 0 && a[i] != b[j]) j = failure[j-1];
            if (a[i] == b[j]) j++;

            // j가 b의 끝까지 도달하면 종료
            if (j >= b.length) {
                exist = true;
                break;
            }
        }

        if (exist) System.out.println(1);
        else System.out.println(0);
    }
}