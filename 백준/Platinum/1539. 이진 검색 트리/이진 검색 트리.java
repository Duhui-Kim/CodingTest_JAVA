import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] depth = new int[N];
        TreeSet<Integer> treeSet = new TreeSet<>();

        long answer = 0;

        for (int i = 0; i < N; i++) {
            int nxt = Integer.parseInt(br.readLine());

            // 더 큰 원소가 없을 경우
            if (treeSet.higher(nxt) == null) {
                // 더 작은 원소도 없다면 가장 처음 원소
                if (treeSet.lower(nxt) == null) {
                    depth[nxt] = 1;
                    treeSet.add(nxt);
                }
                // 더 작은 원소가 있다면 해당 원소의 자녀노드로 들어간다.
                else {
                    depth[nxt] = depth[treeSet.lower(nxt)] + 1;
                    treeSet.add(nxt);
                }
            } else {
                // 더 큰 원소가 있을 때 더 작은 원소가 없다면 큰 원소의 자녀 노드로 들어간다.
                if (treeSet.lower(nxt) == null) {
                    depth[nxt] = depth[treeSet.higher(nxt)] + 1;
                    treeSet.add(nxt);
                }
                // 큰 원소와 작은 원소 모두 존재한다면 더 아래쪽에 위치한 노드에 붙는다.
                else {
                    depth[nxt] = Math.max(depth[treeSet.higher(nxt)], depth[treeSet.lower(nxt)]) + 1;
                    treeSet.add(nxt);
                }
            }
            answer += depth[nxt];
        }
        System.out.println(answer);
    }
}