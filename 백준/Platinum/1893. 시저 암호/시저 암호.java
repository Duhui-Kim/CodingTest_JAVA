import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        // testCase만큼 진행
        for (int tc = 0; tc < testCase; tc++) {
            char[] A = br.readLine().toCharArray();
            char[] W = br.readLine().toCharArray();
            char[] S = br.readLine().toCharArray();

            // 임시로 저장할 tmp 선언
            StringBuilder tmp = new StringBuilder();

            // shift 시키며 진행
            int check = 0;
            for (int i = 0; i < A.length; i++) {
                int cnt = 0;
                if (i == 0) {
                    cnt = findText(W, S);
                } else {
                    W = shift(A, W);
                    cnt = findText(W, S);
                }

                // W를 shift시키고 측정 결과 cnt가 1이면 맞는 암호이므로
                // check 올리고 idx 저장
                if (cnt == 1) {
                    check++;
                    tmp.append(" ").append(i);
                }
            }
            
            // check가 0이면 불가능한 암호
            // 1이면 유일한 암호
            // 2이면 여러개인 암호
            if (check == 0) {
                sb.append("no solution").append("\n");
            } else if (check == 1) {
                sb.append("unique:").append(tmp.toString()).append("\n");
            } else {
                sb.append("ambiguous:").append(tmp.toString()).append("\n");
            }
        }
        System.out.println(sb);
    }

    // 한 칸씩 밀기
    private static char[] shift(char[] a, char[] w) {
        // w의 문자를 a에서 찾아서 일치할 경우 한 칸 밀기
        for (int i = 0; i < w.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (w[i] == a[j]) {
                    if (j+1 >= a.length) j = -1;
                    w[i] = a[j+1];
                    break;
                }
            }
        }
        // 바뀐 문자열 w를 리턴함
        return w;
    }

    // 문자열 찾기!!
    private static int findText(char[] w, char[] s) {
        // 실패 배열 생성
        int[] failure = new int[w.length];

        int j = 0;
        for (int i = 1; i < w.length; i++) {
            while (j > 0 && w[i] != w[j]) j = failure[j-1];
            if (w[i] == w[j]) failure[i] = ++j;
        }

        // 일치하는 문자열의 개수 세기
        int cnt = 0;
        j = 0;
        for (int i = 0; i < s.length; i++) {
            while (j > 0 && s[i] != w[j]) j = failure[j-1];
            if (s[i] == w[j]) ++j;
            if (j == w.length) {
                cnt++;
                j = failure[j-1];
            }
        }

        return cnt;
    }
}