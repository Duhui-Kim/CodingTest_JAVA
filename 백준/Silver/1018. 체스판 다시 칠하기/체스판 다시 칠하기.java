import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = Integer.MAX_VALUE;
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                cnt = Math.min(count(map, i, j), cnt);
            }
        }
        System.out.println(cnt);
    }

    private static int count(char[][] map, int x, int y) {
        int a = 0;
        int b = 0;
        for (int i = x; i < x + 2; i++) {
            if (i == x + 1) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            for (int j = y; j < y + 8; j += 2) {
                if(map[i][j] == 'W') a++;
                if(map[i+2][j] == 'W') a++;
                if(map[i+4][j] == 'W') a++;
                if(map[i+6][j] == 'W') a++;
                if(map[i][j] == 'B') b++;
                if(map[i+2][j] == 'B') b++;
                if(map[i+4][j] == 'B') b++;
                if(map[i+6][j] == 'B') b++;
            }
            for (int j = y+1; j < y + 8; j += 2) {
                if(map[i][j] == 'B') a++;
                if(map[i+2][j] == 'B') a++;
                if(map[i+4][j] == 'B') a++;
                if(map[i+6][j] == 'B') a++;
                if(map[i][j] == 'W') b++;
                if(map[i+2][j] == 'W') b++;
                if(map[i+4][j] == 'W') b++;
                if(map[i+6][j] == 'W') b++;
            }
        }
        if (a < b) return a;
        return b;
    }
}