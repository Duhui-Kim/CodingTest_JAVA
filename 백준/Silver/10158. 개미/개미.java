import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // w, h, x, y, y를 입력받는다.
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int t = Integer.parseInt(br.readLine());
        
        // 이동 후의 좌표 x_move에 기존좌표 x + t를 2 * w로 나눈 나머지를 더해준다.
        // 이동 후의 좌표 y_move에 기존좌표 y + t를 2 * h로 나눈 나머지를 더해준다.
        int x_move = x + t % (2 * w);
        int y_move = y + t % (2 * h);
        
        // x_move와 y_move의 값에 따라 값을 가공한다.
        if(x_move > 2 * w) {
            x_move -= 2 * w;
        } else if(x_move > w) {
            x_move = 2 * w - x_move;
        }

        if(y_move > 2 * h) {
            y_move -= 2 * h;
        } else if(y_move > h) {
            y_move = 2 * h - y_move;
        }

        System.out.println(x_move + " " + y_move);
    }
}
