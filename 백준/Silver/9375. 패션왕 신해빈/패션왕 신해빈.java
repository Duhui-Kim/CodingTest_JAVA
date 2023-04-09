import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 0; tc < testCase; tc++) {
            int N = Integer.parseInt(br.readLine());

            HashMap<String, ArrayList<String>> map = new HashMap<>();
            HashSet<String> set = new HashSet<>();

            StringTokenizer st;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                set.add(b);

                if (map.get(b) == null) {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(a);
                    map.put(b, list);
                } else {
                    map.get(b).add(a);
                }
            }

            if (set.size() == 0) {
                sb.append(0).append("\n");
                continue;
            }

            int answer = 1;
            for(String s : set) {
                answer *= (map.get(s).size() + 1);
            }
            answer--;
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}