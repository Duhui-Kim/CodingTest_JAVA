import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<Integer, String> numMap = new HashMap<>();
        HashMap<String, Integer> strMap = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String poke = br.readLine();

            numMap.put(i, poke);
            strMap.put(poke, i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (input.charAt(0) >= '0' && input.charAt(0) <= '9') {
                sb.append(numMap.get(Integer.parseInt(input)));
            } else {
                sb.append(strMap.get(input));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}