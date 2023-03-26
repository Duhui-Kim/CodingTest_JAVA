import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 계보를 담을 PriorityQueue 배열 선언
        PriorityQueue<String>[] gen = new PriorityQueue[N];

        // indegree 배열 선언
        int[] indegree = new int[N];

        // 이름 입력받을 String배열 선언
        String[] name = new String[N];

        // 사람 이름과 Index를 매칭시키기 위한 Map 선언
        HashMap<String, Integer> map = new HashMap<>();

        // 이름 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            name[i] = st.nextToken();
        }

        // 이름 정렬 후 map에 넣기 (key:이름 = value:Index)
        Arrays.sort(name);
        for (int i = 0; i < N; i++) {
            map.put(name[i], i);
        }

        // 간선 개수
        int M = Integer.parseInt(br.readLine());

        // 간선 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            // indegree값 올려주기
            indegree[map.get(a)]++;

            // 자녀 저장하기
            if(gen[map.get(b)] == null) {
                gen[map.get(b)] = new PriorityQueue<>();
                gen[map.get(b)].add(a);
            } else {
                gen[map.get(b)].add(a);
            }
        }

        // 위상정렬을 시행하기 위해 Queue 선언
        Queue<String> queue = new LinkedList<>();

        // 시조의 수와 이름
        int root = 0;
        String family;

        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        // 입력받으며 시조의 수와 이름 입력받고 queue에도 저장
        for (int i = 0; i < N; i++) {
            if(indegree[i] == 0) {
                root++;
                sb.append(name[i]).append(" ");
                queue.offer(name[i]);
            }
        }
        // 시조 저장하고 sb 초기화
        family = root + "\n";
        family += sb.toString();
        sb.setLength(0);

        // 계보 저장용 리스트
        List<String> save = new ArrayList<>();

        while (!queue.isEmpty()) {
            String nxt = queue.poll();

            // 부모의 이름 저장
            sb.append(nxt).append(" ");

            if (gen[map.get(nxt)] != null) {
                // 자식의 수 저장을 위해 선언
                int cnt = 0;

                while (!gen[map.get(nxt)].isEmpty()) {
                    String child = gen[map.get(nxt)].poll();

                    // indegree가 0이 되었다면 그 때의 자녀 저장 + queue에 저장하기
                    indegree[map.get(child)]--;
                    if(indegree[map.get(child)] == 0) {
                        cnt++;

                        // 자녀 저장
                        tmp.append(child).append(" ");
                        queue.offer(child);
                    }
                }
                // 자녀의 수와 자녀 저장
                sb.append(cnt).append(" ");
                sb.append(tmp.toString());
                tmp.setLength(0);
            }
            // 자식이 없다면 0 저장
            else {
                sb.append(0);
            }

            // save 리스트에 저장하기
            sb.append("\n");
            save.add(sb.toString());
            sb.setLength(0);
        }


        // 중복된 이름이 없으므로 자녀의 이름은 이미 정렬되어있으니 부모의 이름 순으로 정렬하면 된다.
        Collections.sort(save);
        for(String s : save) {
            sb.append(s);
        }

        // 결과 출력
        System.out.println(family);
        System.out.println(sb);
    }
}