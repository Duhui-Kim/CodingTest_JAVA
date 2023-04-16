import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static class Stone {
        int weight, price;
        private Stone(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Stone> queue = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);

        // 보석 가격 내림차순으로 queue에 담기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queue.offer(new Stone(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 가방 저장용 map 선언
        TreeMap<Integer, Integer> map = new TreeMap<>();

        // 가방 저장하기
        for (int i = 0; i < K; i++) {
            int bag = Integer.parseInt(br.readLine());

            // 같은 무게의 가방이 여러 개일 수 있으므로 value에 가방 개수 담기
            if (map.get(bag) == null) {
                map.put(bag, 1);
            } else {
                map.replace(bag, map.get(bag) + 1);
            }
        }

        long answer = 0;
        while (!map.isEmpty() && !queue.isEmpty()) {
            Stone stone = queue.poll();

            // 해당 무게를 담을 가방이 있을 경우
            if (map.ceilingKey(stone.weight) != null) {
                Map.Entry<Integer, Integer> tmp = map.ceilingEntry(stone.weight);

                // value가 1이면 해당 가방 1개이므로 제거
                if (tmp.getValue() == 1) map.remove(tmp.getKey());
                // value가 1보다 큰 경우 가방 하나 없애줌
                else map.replace(tmp.getKey(), tmp.getValue()-1);

                // 정답에 가격 더해주기
                answer = answer + (long) stone.price;
            }
        }

        System.out.println(answer);
    }
}