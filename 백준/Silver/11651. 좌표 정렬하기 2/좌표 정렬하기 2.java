import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Pair implements Comparable<Pair> {
        int x;
        int y;
        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return x + " " + y;
        }

        @Override
        public int compareTo(Pair o) {
            if (y == o.y) return Integer.compare(x, o.x);
            return Integer.compare(y, o.y);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Pair> list = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        System.out.println(sb);
    }
}