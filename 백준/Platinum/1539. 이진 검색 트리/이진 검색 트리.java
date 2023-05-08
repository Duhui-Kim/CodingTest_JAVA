import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    private static class Tree {
        int depth, num;
        boolean right;
        private Tree(int num, int depth) {
            this.num = num;
            this.depth = depth;
        }
    }
    private static int answer = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        TreeMap<Integer, Tree> treeMap = new TreeMap<>();
        int init = Integer.parseInt(br.readLine());

        long answer = 1;
        treeMap.put(init, new Tree(init, 1));

        for (int i = 1; i < N; i++) {
            int nxt = Integer.parseInt(br.readLine());

            if (treeMap.floorEntry(nxt) != null) {
                Tree floor = treeMap.floorEntry(nxt).getValue();
                if (!floor.right) {
                    floor.right = true;
                    treeMap.replace(floor.num, floor);
                    treeMap.put(nxt, new Tree(nxt, floor.depth + 1));
                    answer += floor.depth + 1;
                    continue;
                }
            }

            Tree ceil = treeMap.ceilingEntry(nxt).getValue();
            treeMap.put(nxt, new Tree(nxt, ceil.depth + 1));
            answer += ceil.depth + 1;
        }
        System.out.println(answer);
    }
}