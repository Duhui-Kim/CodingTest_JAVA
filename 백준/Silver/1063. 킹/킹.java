import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] tmpKing = st.nextToken().toCharArray();
        char[] tmpStone = st.nextToken().toCharArray();

        int[] king = new int[] {'8' - tmpKing[1], tmpKing[0] - 'A'};
        int[] stone = new int[] {'8' - tmpStone[1], tmpStone[0] - 'A'};

        int N = Integer.parseInt(st.nextToken());

        // 7 0 1
        // 6 - 2
        // 5 4 3
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        Map<String, Integer> cmd = new HashMap<>();
        cmd.put("T", 0);
        cmd.put("RT", 1);
        cmd.put("R", 2);
        cmd.put("RB", 3);
        cmd.put("B", 4);
        cmd.put("LB", 5);
        cmd.put("L", 6);
        cmd.put("LT", 7);

        for (int i = 0; i < N; i++) {
            int dir = cmd.get(br.readLine());

            int x = king[0] + dx[dir];
            int y = king[1] + dy[dir];

            if(x < 0 || y < 0 || x >= 8 || y >= 8) continue;

            int sx = stone[0] + dx[dir];
            int sy = stone[1] + dy[dir];

            if (x == stone[0] && y == stone[1]) {
                if(sx < 0 || sy < 0 || sx >= 8 || sy >= 8) continue;
                else {
                    king[0] = x;
                    king[1] = y;
                    stone[0] = sx;
                    stone[1] = sy;
                }
            } else {
                king[0] = x;
                king[1] = y;
            }
        }
        System.out.print((char) (king[1] + 'A'));
        System.out.println(8 - king[0]);
        System.out.print((char) (stone[1] + 'A'));
        System.out.println(8 - stone[0]);
    }
}