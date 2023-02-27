import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int max;
    private static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N];
        int[] operator = new int[N-1];
        int[] inputOper = new int[4];

        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        // 숫자를 입력받는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자의 개수를 입력받는다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            inputOper[i] = Integer.parseInt(st.nextToken());
        }

        // 숫자와 연산자 개수, 연산자를 담을 배열과 0을 넣는다.
        start(num, inputOper, operator, 0);
        System.out.println(max + "\n" + min);
    }

    private static void start(int[] num, int[] inputOper, int[] operator, int k) {

        // k가 연산자 배열의 크기와 같아지면 계산결과를 max, min과 비교하여 값을 바꾼다.
        if(k == operator.length) {
            int answer = calculate(num, operator);
            max = answer > max ? answer: max;
            min = answer < min ? answer: min;
            return;
        }

        // inputOper는 연산자를 담고 있는 배열이므로 0 ~ 4까지 반복한다.
        for (int i = 0; i < 4; i++) {
            // inputOper에 0이 아닌 값이 들어있으면 그 연산자를 사용하고 카운트를 1 줄인다.
            // 재귀 후에는 다시 연산자를 추가해준다.
            if(inputOper[i] != 0) {
                inputOper[i]--;
                operator[k] = i;
                start(num, inputOper, operator, k+1);
                inputOper[i]++;
            }
        }
        
    }

    // 수와 연산자가 주어지면 계산하는 method
    private static int calculate(int[] num, int[] operator) {
        int add = 0;
        int sub = 1;
        int multi = 2;
        int div = 3;

        int answer = num[0];

        // 연산자의 수는 항상 숫자보다 작으므로 연산자의 수만큼 반복한다.
        for(int i=0; i<operator.length; i++) {
            // + 일 경우 i+1번째 수를 더한다.
            if(operator[i] == add) {
                answer += num[i+1];

            // - 일 경우 i+1번째 수를 뺀다.
            } else if (operator[i] == sub) {
                answer -= num[i+1];

            // * 일 경우 i+1번째 수를 곱한다.
            } else if (operator[i] == multi) {
                answer *= num[i+1];

            // / 일 경우 i+1번째 수를 나누는데, 이 때 i번째 수가 음수이면
            // 양수로 만들어서 계산 후 음수로 다시 바꿔준다.
            } else if (operator[i] == div) {
                if(answer < 0) {
                    answer = ((answer * -1) / num[i+1]) * -1;
                } else {
                    answer /= num[i+1];
                }
            }
        }
        return answer;
    }
}
