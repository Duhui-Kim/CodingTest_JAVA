import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // A의 왼쪽아래 a1, a2
            int a1 = Integer.parseInt(st.nextToken());
            int a2 = Integer.parseInt(st.nextToken());
            // A의 오른쪽위 b1, b2
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());

            // B의 왼쪽아래 c1, c2
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            // B의 오른쪽위 c3, c4
            int d1 = Integer.parseInt(st.nextToken());
            int d2 = Integer.parseInt(st.nextToken());

            // 공통부분이 없는 경우
            if((a1 > d1) || (a2 > d2) || (c1 > b1) || (c2 > b2)) {
                System.out.println('d');

            // 점인 경우 (네 점 비교)
            } else if((a1==d1 && a2==d2) || (a1==d1 && c2==b2) || (b1==c1 && b2==c2) || (b1==c1 && a2==d2)) {
                System.out.println('c');

            // 선분인 경우 (공통부분없는 경우와 점인 경우는 제외되었으니 한 좌표가 일치할 때 선분)
            } else if(a1==d1 || a2==d2 || b1==c1 || b2==c2) {
                System.out.println('b');

            // 세 경우가 모두 아닐 때 직사각형
            } else {
                System.out.println('a');
            }

        }


    }
}
