import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자 입력받기
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        // 배열 값 저장할 공간 만들기
        int[][] map = new int[A.length+1][B.length+1];

        // max일 때의 값과 좌표 미리 저장
        int[] max = new int[3];

        // 반복문 돌며 map 채우기
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                // 값이 같으면 i-1, j-1에서 +1한 값 넣기
                if(A[i-1] == B[j-1]) {
                    map[i][j] = map[i-1][j-1] + 1;
                }
                // 값이 다르면 i-1, j 또는 i, j-1 중 더 큰 값 가져오기
                else {
                    map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
                }
                // 최댓값과 그 때의 좌표 저장하기
                if(map[i][j] > max[0]) {
                    max[0] = map[i][j];
                    max[1] = i;
                    max[2] = j;
                }
            }
        }
        if(max[0] == 0) {
            System.out.println(0);
            return;
        }

        // max 값을 일단 StringBuilder에 저장
        StringBuilder sb = new StringBuilder();
        sb.append(max[0]).append("\n");

        // 시작점은 max가 되었던 부분부터 시작
        int x = max[1];
        int y = max[2];

        // 부분수열에 들어갈 값 찾기
        Stack<Character> stack = new Stack<>();
        while (map[x][y] > 0) {
            // x-1, y값과 x, y-1 값이 같으면 해당 문자를 stack 넣고,
            // 위치를 x-1, y-1로 조정한다.
            if(map[x][y] == map[x-1][y] + 1 && map[x-1][y] == map[x][y-1]) {
                stack.push(A[x-1]);
                x--;
                y--;
            }
            // 두 값이 같지 않으면 두 값 중 현재 위치와 같은 값을 가지는 곳으로 간다.
            else if (map[x][y] == map[x-1][y]) {
                x--;
            } else if(map[x][y] == map[x][y-1]){
                y--;
            }
        }
        // stack에 있는 문자들을 꺼내면서 sb에 저장
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
    }
}