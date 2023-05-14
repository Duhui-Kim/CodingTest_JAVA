import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        TreeSet<Integer> treeSet = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        // 명소일 경우에만 treeSet에 넣어줌
        for (int i = 1; i <= N; i++) {
            if (st.nextToken().equals("1")) {
                treeSet.add(i);
            }
        }

        int startPoint = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "1":
                    // 명소 지정이 되어있다면 해제, 되어있지않다면 등록
                    int nxt = Integer.parseInt(st.nextToken());
                    if (!treeSet.isEmpty() && treeSet.contains(nxt)) {
                        treeSet.remove(nxt);
                    } else {
                        treeSet.add(nxt);
                    }
                    break;
                case "2":
                    // 시작지점 이동하기
                    int move = Integer.parseInt(st.nextToken());
                    startPoint = startPoint + move;
                    if (startPoint % N == 0) {
                        startPoint = N;
                    } else {
                        startPoint %= N;
                    }
                    break;
                case "3":
                    // 명소가 없다면 -1 출력 후 지나가기
                    if (treeSet.isEmpty()) {
                        sb.append(-1).append("\n");
                        continue;
                    }
                    int num = 0;
                    if(treeSet.ceiling(startPoint) != null) {
                        num = treeSet.ceiling(startPoint);
                    };
                    // 시작지점 오른쪽에 숫자가 없다면 왼쪽에서 찾기
                    if (num == 0) {
                        num = treeSet.ceiling(1);
                        num = num + N;
                    }
                    // 거리 출력하기
                    sb.append(num - startPoint).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}