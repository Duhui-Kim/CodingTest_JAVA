import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        ArrayList<String> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < L; i++) {
            String input = br.readLine();

            list.add(input);

            if (map.get(input) == null) {
                map.put(input, 1);
            } else {
                map.put(input, map.get(input)+1);
            }
        }

        StringBuilder sb = new StringBuilder();

        int cnt = 0;
        for (int i = 0; i < L; i++) {
            if (map.get(list.get(i)) == 1) {
                sb.append(list.get(i)).append("\n");
                cnt++;
            } else {
                map.put(list.get(i), map.get(list.get(i))-1);
            }
            if (cnt == K) break;
        }
        System.out.println(sb);
    }
}