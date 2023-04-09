import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();

        int N = input.length;

        String[] arr = new String[N];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sb.append(input[j]);
            }
            arr[i] = sb.toString();
            sb.setLength(0);
        }
        Arrays.sort(arr);

        for (int i = 0; i < N; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.println(sb);
    }
}