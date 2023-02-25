import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 테스트 케이스를 입력받는다.
        int testCase = Integer.parseInt(br.readLine());
        
        for (int t = 0; t < testCase; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 딱지를 저장할 배열을 생성한다.
            int[] answer = new int[5];
            
            // A의 딱지를 입력받는다.
            int numA = Integer.parseInt(st.nextToken());
            for (int i = 0; i < numA; i++) {
                answer[Integer.parseInt(st.nextToken())]++;
            }

            // B의 딱지를 입력받는다.
            st = new StringTokenizer(br.readLine());
            int numB = Integer.parseInt(st.nextToken());
            for (int i = 0; i < numB; i++) {
                answer[Integer.parseInt(st.nextToken())]--;
            }
            
            // 비겼을 경우를 확인하기 위해 boolean 변수를 선언하고
            // 큰 숫자부터 승부가 판가름 나기 때문에 뒤에서부터 체크한다.
            boolean draw = true;
            for (int i = 4; i >= 1; i--) {
                if(answer[i] > 0) {
                    bw.write("A\n");
                    draw = false;
                    break;
                } else if (answer[i] < 0) {
                    bw.write("B\n");
                    draw = false;
                    break;
                }
            }
            if (draw) bw.write("D\n");
        }
        bw.close();
    }
}
