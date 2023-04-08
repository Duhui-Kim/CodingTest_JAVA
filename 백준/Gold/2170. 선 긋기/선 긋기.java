import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Line {
        int st, ed;
        private Line(int st, int ed) {
            this.st = st;
            this.ed = ed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Line> list = new ArrayList<>();

        // 선 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // st는 오름차순으로 정렬하고, ed는 내림차순 정렬
        Collections.sort(list, (o1, o2) -> {
            if(o1.st == o2.st) return o2.ed - o1.ed;
            return o1.st - o2.st;
        });

        int start = list.get(0).st;
        int end = list.get(0).ed;
        long lineCnt = 0;

        for (int i = 1; i < N; i++) {
            // ed는 내림차순 정렬되어있으므로 시작점이 같으면 안봐도 됨
            if (start == list.get(i).st) continue;

            // end보다 다음 라인의 출발점이 작거나 같은 경우
            if (end >= list.get(i).st) {
                // 끝점을 늘려줌
                if (list.get(i).ed > end) {
                    end = list.get(i).ed;
                }
            }
            // end보다 다음 라인의 출발점이 큰 경우
            // 지금 라인의 길이를 더해주고, start와 end 변경
            else {
                lineCnt += end - start;
                start = list.get(i).st;
                end = list.get(i).ed;
            }
        }
        // 마지막에 더해주기
        lineCnt += end - start;
        
        System.out.println(lineCnt);
    }
}