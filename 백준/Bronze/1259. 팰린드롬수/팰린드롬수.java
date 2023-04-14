import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        loop:
        while (true) {
            char[] input = br.readLine().toCharArray();

            if (input[0] == '0') break;

            int left = 0;
            int right = input.length-1;

            while (left <= right) {
                if (input[left] == input[right]) {
                    left++;
                    right--;
                } else {
                    sb.append("no").append("\n");
                    continue loop;
                }
            }
            sb.append("yes").append("\n");
        }
        System.out.println(sb);
    }
}