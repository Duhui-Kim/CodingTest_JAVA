import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();

        long answer = 0;
        for (int i = 0; i < N; i++) {
            answer = (answer + (((long) input[i] - 'a' + 1) * (long) Math.pow(31, i))) % 1234567891;
        }

        System.out.println(answer);
    }
}