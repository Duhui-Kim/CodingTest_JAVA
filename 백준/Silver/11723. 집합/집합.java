import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] check = new boolean[21];

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String cmd = br.readLine();

            switch (cmd.split(" ")[0]) {
                case "add":
                    check[Integer.parseInt(cmd.split(" ")[1])] = true;
                    break;
                case "remove":
                    check[Integer.parseInt(cmd.split(" ")[1])] = false;
                    break;
                case "check":
                    if (check[Integer.parseInt(cmd.split(" ")[1])]) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case "toggle":
                    check[Integer.parseInt(cmd.split(" ")[1])] = !check[Integer.parseInt(cmd.split(" ")[1])];
                    break;
                case "all":
                    Arrays.fill(check, true);
                    break;
                case "empty":
                    Arrays.fill(check, false);
                    break;
            }
        }
        System.out.println(sb);
    }
}