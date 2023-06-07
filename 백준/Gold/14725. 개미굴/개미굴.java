import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static class Food {
        TreeMap<String, Food> subFood;

        public Food() {
            this.subFood = new TreeMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Food root = new Food();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int depth = Integer.parseInt(st.nextToken());

            String[] foods = new String[depth];
            for (int j = 0; j < depth; j++) {
                foods[j] = st.nextToken();
            }
            insert(root, foods, 0, depth);
        }

        StringBuilder sb = new StringBuilder();
        printFood(root, sb, "");

        System.out.println(sb.toString());
    }

    private static void printFood(Food root, StringBuilder sb, String line) {

        for(String s : root.subFood.navigableKeySet()) {
            sb.append(line + s).append("\n");
            printFood(root.subFood.get(s), sb, "--" + line);
        }
    }

    private static void insert(Food root, String[] foods, int k, int depth) {
        if(k == depth) return;

        if (root.subFood.get(foods[k]) == null) root.subFood.put(foods[k], new Food());

        insert(root.subFood.get(foods[k]), foods, k+1, depth);
    }
}