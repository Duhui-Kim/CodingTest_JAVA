import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        double x, y;
        private Pair(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 좌표의 개수 입력받기
        int N = Integer.parseInt(br.readLine());

        // 꼭짓점 넣을 배열
        Pair[] points = new Pair[N+1];

        // 꼭짓점 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            points[i] = new Pair(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
        }

        // 신발끈 공식 활용하기
        points[N] = points[0];
        double area = 0;
        for (int i = 0; i < N; i++) {
            area += points[i].x * points[i+1].y;
            area -= points[i+1].x * points[i].y;
        }
        area = Math.abs(area) / 2.0;

        System.out.println(String.format("%.1f", Math.round(area * 10.0)/10.0));
    }
}