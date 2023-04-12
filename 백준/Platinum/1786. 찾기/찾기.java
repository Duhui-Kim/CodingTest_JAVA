import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] T = br.readLine().toCharArray();
        char[] P = br.readLine().toCharArray();

        int[] failure = new int[P.length];

        int j = 0;
        for (int i = 1; i < P.length; i++) {
            while (j > 0 && P[i] != P[j]) j = failure[j-1];
            if (P[i] == P[j]) failure[i] = ++j;
        }

        StringBuilder sb = new StringBuilder();

        int cnt = 0;

        j = 0;
        for (int i = 0; i < T.length; i++) {
            while (j > 0 && T[i] != P[j]) j = failure[j-1];
            if (T[i] == P[j]) ++j;
            if (j == P.length) {
                cnt++;
                sb.append((i+2)-j).append(" ");
                j = failure[j-1];
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}