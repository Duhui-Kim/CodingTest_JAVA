import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int whiteCnt = 0;
    private static int blackCnt = 0;
    private static int N;
    private static int[] dx = {1, 1, -1, -1};
    private static int[] dy = {-1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Map<Integer, int[]> input = new HashMap<>();
        List<int[]> white = new ArrayList<>();
        List<int[]> black = new ArrayList<>();

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    if ((i + j) % 2 == 0) {
                        white.add(new int[] {i, j});
                    } else {
                        black.add(new int[] {i, j});
                    }
                }
            }
        }
        // 하얀칸부터 진행
        backTracking(white, input, 0, 0, 1);
        // 검정칸 진행
        backTracking(black, input, 0, 0, 0);

        System.out.println(whiteCnt + blackCnt);
    }

    private static void backTracking(List<int[]> map, Map<Integer,int[]> input, int k, int cnt, int color) {
        // 하얀칸 / 검정칸 별 최댓값 저장
        if (k == map.size()) {
            if (color == 1) {
                whiteCnt = Math.max(whiteCnt, cnt);
            } else {
                blackCnt = Math.max(blackCnt, cnt);
            }
            return;
        }

        int x = map.get(k)[0];
        int y = map.get(k)[1];

        if (check(input, x, y, k)) {
            input.put(k, new int[] {x, y});
            backTracking(map, input, k+1, cnt+1, color);
            input.remove(k);
        }
        backTracking(map, input, k+1, cnt, color);
    }

    private static boolean check(Map<Integer, int[]> input, int x, int y, int k) {
        // 반복문을 돌며 list에 들어있는 좌표의 x값과 y값의 차이의 절댓값이 같은 경우
        // 대각선 상에 있는 것이므로 놓을 수 없다.
        for (int i = 0; i < k; i++) {
            if (input.get(i) == null) continue;

            int[] arr= input.get(i);
            if(Math.abs(arr[0] - x) == Math.abs(arr[1] - y)) {
                return false;
            }
        }
        return true;
    }
}