import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
    1. 실패배열 구하기
    2. (이름의 길이 - 실패배열의 마지막값) * N-1 + 이름의 길이가 최소 길이
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] input = st.nextToken().toCharArray();
        long N = Long.parseLong(st.nextToken());

        long len = input.length;

        long[] failure = new long[(int) len];
        int j = 0;
        for (int i = 1; i < len; i++) {
            while (j > 0 && input[i] != input[j]) j = (int) failure[j-1];
            if (input[i] == input[j]) failure[i] = ++j;
        }

        System.out.println(((len - failure[(int) (len-1)]) * (N-1) + len));
    }
}
