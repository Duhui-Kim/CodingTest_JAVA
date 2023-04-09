import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 스도쿠 맵
        int[][] map = new int[9][9];

        // map 입력받기 (map을 입력받으면서 0의 개수를 세준다)
        int cnt = 0;
        for (int i = 0; i < 9; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 9; j++) {
                map[i][j] = tmp[j] - '0';
                if (map[i][j] == 0) cnt++;
            }
        }
        // 백트래킹 진행
        sb = new StringBuilder();
        backTracking(map, 0, cnt, 0, 0);

        System.out.println(sb);
    }

    private static void backTracking(int[][] map, int k, int cnt, int x, int y) {
        // stringBuilder에 값이 입력돼있다면 종료
        if (sb.length() != 0) return;

        // 0이 모두 채워졌을 때, stringBuilder에 담기
        if (k == cnt) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            return;
        }

        // y가 범위 밖이면 0으로 만들고 다음 줄로
        if (y > 8) {
            x++;
            y = 0;
            if (x > 8) return;
        }

        // map의 값이 0이 아니면 다음 칸으로
        if (map[x][y] != 0) {
            backTracking(map, k, cnt, x, y+1);
            return;
        }

        // 이번 칸에 넣을 수 있는 숫자들 가져오기
        ArrayList<Integer> list = findNum(map, x, y);
        
        // 없다면 리턴
        if (list.size() == 0) return;

        // 숫자 작은것부터 넣어주고, 백트래킹
        for(int a : list) {
            map[x][y] = a;
            backTracking(map, k+1, cnt, x, y+1);
            map[x][y] = 0;
        }
    }

    private static ArrayList<Integer> findNum(int[][] map, int r, int c) {
        boolean[] check = new boolean[10];

        for (int k = 0; k < 9; k++) {
            check[map[r][k]] = true;
            check[map[k][c]] = true;
        }

        int nr = r / 3 * 3;
        int nc = c / 3 * 3;

        for (int i = nr; i < nr + 3; i++) {
            for (int j = nc; j < nc + 3; j++) {
                check[map[i][j]] = true;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if (!check[i]) {
                list.add(i);
            }
        }
        return list;
    }
}