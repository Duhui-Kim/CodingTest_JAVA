import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static class Tree {
        List<Integer> child;
        public Tree() {
            this.child = new ArrayList<>();
        };
    }
    public static int leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        leaf = 0;

        Tree[] trees = new Tree[N];

        for(int i=0; i<N; i++) {
            trees[i] = new Tree();
        }

        int ROOT = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent < 0) {
                ROOT = i;
                continue;
            };

            trees[parent].child.add(i);
        }
        int remove = Integer.parseInt(br.readLine());
        trees[remove] = null;

        if (N == 1) {
            System.out.println(0);
            return;
        }

        findLeaf(trees, trees[ROOT]);

        System.out.println(leaf);
    }

    private static void findLeaf(Tree[] trees, Tree tree) {
        if (tree == null) return;
        if (tree.child.size() == 0) {
            leaf++;
            return;
        }
        boolean isLeaf = true;

        for (int nxt : tree.child) {
            if (trees[nxt] == null) continue;;

            isLeaf = false;
            findLeaf(trees, trees[nxt]);
        }

        if (isLeaf) leaf++;
    }

}