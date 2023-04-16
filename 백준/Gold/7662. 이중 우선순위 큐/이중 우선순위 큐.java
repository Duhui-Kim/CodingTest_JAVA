import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        // 테스트케이스만큼 반복 진행
        for (int tc = 0; tc < testCase; tc++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();

            int N = Integer.parseInt(br.readLine());

            // 명령문 입력받기
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();

                // I일 경우 해당 숫자 입력하기 (동일한 숫자가 입력될 수 있으므로 value에 개수를 저장)
                if (cmd.equals("I")) {
                    int num = Integer.parseInt(st.nextToken());

                    if (map.get(num) == null) {
                        map.put(num, 1);
                    } else {
                        map.replace(num, map.get(num)+1);
                    }
                } 
                // D일 경우 제거하는 연산이므로 비어있을 경우 지나가고, 아닐 경우 값 제거하기
                // 동일한 값이 여러 개 있을 경우 하나만 제거하기 위해 value값 1 줄여주기
                else {
                    int num = Integer.parseInt(st.nextToken());

                    if (map.isEmpty()) continue;

                    if (num < 0) {
                        if (map.firstEntry().getValue() == 1) {
                            map.pollFirstEntry();
                        } else {
                            map.replace(map.firstEntry().getKey(), map.firstEntry().getValue() - 1);
                        }
                    } else {
                        if (map.lastEntry().getValue() == 1) {
                            map.pollLastEntry();
                        } else {
                            map.replace(map.lastEntry().getKey(), map.lastEntry().getValue() - 1);
                        }
                    }
                }
            }
            // map이 비어있다면 EMPTY 출력 / 아닐 경우 최댓값, 최솟값 출력
            if (map.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(map.lastKey() + " " + map.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}