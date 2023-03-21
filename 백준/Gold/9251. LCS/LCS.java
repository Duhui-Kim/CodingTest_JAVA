import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();

        // map 생성 시 padding을 둔다.
        int[][] map = new int[A.length+1][B.length+1];

        // 나중에 순회하지 않고 max 값을 출력하기 위해 값을 저장하면서 max를 저장한다.
        int max = 0;
        
        // 이중 반복문을 진행
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <= B.length; j++) {
                // A와 B의 문자열이 같으면 i-1, j-1의 숫자에서 +1 한 값을 가져온다.
                if(A[i-1] == B[j-1]) {
                    map[i][j] = map[i-1][j-1] + 1;
                }
                // A와 B의 문자열이 다르면, i-1 또는 j-1의 숫자 중 큰 값을 가져온다.
                else {
                    map[i][j] = Math.max(map[i-1][j], map[i][j-1]);
                }
                // 최댓값을 저장
                max = Math.max(max, map[i][j]);
            }
        }
        // 최댓값 출력
        System.out.println(max);
    }
}