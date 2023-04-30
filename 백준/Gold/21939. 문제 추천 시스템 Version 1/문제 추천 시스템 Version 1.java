import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<Integer, TreeSet<Integer>> recommend = new TreeMap<>();
        HashMap<Integer, Integer> problem = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        // N개의 문제를 입력받으며 진행
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            if (recommend.get(L) == null) {
                recommend.put(L, new TreeSet<>());
            }
            // L 난이도의 문제집에 P 넣기
            recommend.get(L).add(P);
            // 문제 P가 어느 난이도에 저장되었는지 넣기
            problem.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            int P, L;

            switch (cmd) {
                case "add":
                    P = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());

                    // 문제번호 P이고 난이도 L인 문제를 입력
                    if (recommend.get(L) == null) {
                        recommend.put(L, new TreeSet<>());
                    }
                    recommend.get(L).add(P);
                    problem.put(P, L);
                    break;
                case "recommend":
                    int x = Integer.parseInt(st.nextToken());

                    // x가 1이면 가장 난이도 높은 문제의 가장 번호 큰 문제 출력
                    // -1일 경우 반대
                    if (x == 1) {
                        sb.append(recommend.lastEntry().getValue().last()).append("\n");
                    } else {
                        sb.append(recommend.firstEntry().getValue().first()).append("\n");
                    }

                    break;
                case "solved":
                    P = Integer.parseInt(st.nextToken());
                    L = problem.get(P);

                    // 만약 해당 원소가 하나뿐이라면 recommend의 entry 자체를 삭제함
                    if(recommend.get(L).size() == 1) recommend.remove(L);
                    else recommend.get(L).remove(P);
                    
                    // problem 목록에서 삭제
                    problem.remove(P);
                    break;
            }
        }
        System.out.println(sb);
    }
}
