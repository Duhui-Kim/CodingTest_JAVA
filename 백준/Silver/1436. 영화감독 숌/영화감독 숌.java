import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int num = 665;
        int cnt = 0;
        while (cnt < N) {
            num++;
            if(Integer.toString(num).contains("666")) {
                cnt++;
            }
        }
        System.out.println(num);
    }
}