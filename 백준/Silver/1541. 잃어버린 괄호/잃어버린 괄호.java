import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        StringBuilder sb = new StringBuilder();

        int answer = 0;
        boolean minus = false;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) <= '9' && input.charAt(i) >= '0') {
                sb.append(input.charAt(i));
            } else if (input.charAt(i) == '-' || input.charAt(i) == '+') {
                if (minus) {
                    answer -= Integer.parseInt(sb.toString());
                } else {
                    answer += Integer.parseInt(sb.toString());
                }
                sb.setLength(0);
            }
            if (input.charAt(i) == '-') {
                minus = true;
            }
        }
        if (minus) {
            answer -= Integer.parseInt(sb.toString());
        } else {
            answer += Integer.parseInt(sb.toString());
        }
        System.out.println(answer);
    }
}