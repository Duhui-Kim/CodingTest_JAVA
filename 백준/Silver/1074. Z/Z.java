import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int r;
    private static int c;
    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, r, c를 입력받는다.
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = 1;
        for (int i = 0; i < N; i++) {
            size *= 2;
        }

        // zMethod를 만들어 진행한다.
        zMethod(0, 0, size, size, N, 0, size*size);
    }

    private static void zMethod(int x1, int y1, int x2, int y2, int k, int start, int end) {
        // k가 N에 도달하면 종료한다.
        if(k == 0) {
            cnt++;
            if(x1 == r && y1 == c) System.out.println(start);
            return;
        }

        // N에 도달하지 않았을 때는 4등분하여서 진행한다.
        int midx = (x2+x1)/2;
        int midy = (y2+y1)/2;

        int devide = (end - start) / 4;

        if(r < midx && c < midy) {
            zMethod(x1, y1, midx, midy, k-1, start, start + devide);
        } else if (r < midx && c >= midy) {
            zMethod(x1, midy, midx, y2, k-1, start + devide, start + devide * 2);
        } else if (r >= midx && c < midy) {
            zMethod(midx, y1, x2, midy, k-1, start + devide * 2, start + devide * 3);
        } else {
            zMethod(midx, midy, x2, y2, k-1, start + devide * 3, end);
        }
    }
}