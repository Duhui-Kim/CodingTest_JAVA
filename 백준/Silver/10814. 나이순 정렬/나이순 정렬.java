import java.util.*;

public class Main {
    static class Pair {
        int num;
        String name;
        private Pair(int num, String name) {
            this.num = num;
            this.name = name;
        }
        public String toString() {
            return this.num + " " + this.name;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = Integer.parseInt(sc.nextLine());

        StringTokenizer st;
        ArrayList<Pair> list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(sc.nextLine());
            list.add(new Pair(Integer.parseInt(st.nextToken()), st.nextToken()));
        }
        Collections.sort(list, (o1, o2) -> o1.num - o2.num);

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        System.out.println(sb);
    }
}