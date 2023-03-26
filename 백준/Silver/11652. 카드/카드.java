import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Long, Integer> map = new HashMap<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        int max = 0;
        long minNum = 0;

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            if(map.get(num) == null) {
                map.put(num, 1);
            } else {
                map.replace(num, map.get(num)+1);
            }
            if(map.get(num) > max) {
                max = map.get(num);
                minNum = num;
            } else if (map.get(num) == max) {
                if(num >= minNum) continue;
                minNum = num;
            }
        }
        System.out.println(minNum);
    }
}