import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 두 문자열을 입력받는다.
        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        // 실패함수 선언
        int[] failure = new int[b.length];

        // b 문자열에 대한 실패함수 생성
        int j = 0;
        for (int i = 1; i < b.length; i++) {
            while (j > 0 && b[i] != b[j]) j = failure[j-1];
            if (b[i] == b[j]) failure[i] = ++j;
        }

        // 정답 저장할 배열 (사실 없어도 됌)
        int[] answer = new int[a.length];

        // 두 문자열을 진행하며 부분문자열이 있는지 체크
        j = 0;
        int cnt = 0;
        for (int i = 0; i < a.length; i++) {
            while (j > 0 && a[i] != b[j]) j = failure[j-1];
            if (a[i] == b[j]) answer[i] = ++j;
            if (j >= b.length) {
                cnt++;
                j = failure[j-1];
            }
        }
        if (cnt > 0) System.out.println(1);
        else System.out.println(0);
    }
}