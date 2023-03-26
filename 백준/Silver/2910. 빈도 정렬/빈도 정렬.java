import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        HashMap<Long, long[]> map = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            if(map.get(num) == null) {
                long[] arr = new long[3];
                arr[0] = i;
                arr[1] = 1;
                arr[2] = num;
                map.put(num, arr);
            } else {
                long[] arr2 = map.get(num);
                arr2[1]++;
                map.replace(num, arr2);
            }
        }

        List<long[]> list = new ArrayList<>();

        for (long[] a: map.values()) {
            list.add(a);
        }

        Collections.sort(list, (o1, o2) -> {
            if(o1[1] != o2[1]) return Math.toIntExact(o2[1] - o1[1]);
            else return Math.toIntExact(o1[0] - o2[0]);
        });

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i)[1]; j++) {
                sb.append(list.get(i)[2]).append(" ");
            }
        }
        System.out.println(sb);
    }
}