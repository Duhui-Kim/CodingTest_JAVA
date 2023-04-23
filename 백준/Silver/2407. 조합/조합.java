import java.io.*;
import java.util.*;
import java.math.BigInteger;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        BigInteger big1 = BigInteger.ONE;
        BigInteger big2 = BigInteger.ONE;

        for (int i = 0; i < M; i++) {
            big1 = big1.multiply(new BigInteger(String.valueOf(N - i)));
            big2 = big2.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        BigInteger answer = big1.divide(big2);

        System.out.println(answer);
    }
}