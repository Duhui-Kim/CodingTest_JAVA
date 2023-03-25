import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        char[] C = br.readLine().toCharArray();

        int a = A.length+1;
        int b = B.length+1;
        int c = C.length+1;

        // 문자열 길이 저장을 위한 배열
        int[][][] answer = new int[a][b][c];

        // 최댓값 저장을 위한 인자
        int max = 0;

        // 3중 포문을 돌며 세 단어 내의 글자가 동일하면 i-1, j-1, k-1에 적힌 숫자 + 1
        // 그렇지 않으면 i, j, k에서 각각 1을 뺀 곳의 value 중 최댓값 가져오기
        for (int i = 1; i < a; i++) {
            for (int j = 1; j < b; j++) {
                for (int k = 1; k < c; k++) {
                    if(A[i-1] == B[j-1] && B[j-1] == C[k-1]) {
                        answer[i][j][k] = answer[i-1][j-1][k-1] + 1;
                    } else {
                        answer[i][j][k] = Math.max(answer[i-1][j][k], Math.max(answer[i][j-1][k], answer[i][j][k-1]));
                    }
                    max = Math.max(answer[i][j][k], max);
                }
            }
        }
        System.out.println(max);
    }
}