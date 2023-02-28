import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // min 값을 찾기 위해 min을 아주 큰 값으로 선언
    // 입력된 점수를 method에서도 쓰기 위해 static으로 선언
    private static int min = Integer.MAX_VALUE;
    private static int[][] point;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사람의 수 N을 입력받고, 0에서부터 N-1까지 사람을 arr에 채운다.
        int N = Integer.parseInt(br.readLine());

        int[] people = new int[N];
        for (int i = 0; i < N; i++) {
            people[i] = i;
        }

        // Point를 입력받을 point 2차 배열을 만든다.
        point = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                point[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 사람을 A팀 B팀으로 나눌 것이기 때문에 사람 수의 절반 크기의 배열을 만들고,
        int[] A = new int[N/2];

        // 팀을 두 개로 나누는 method를 실행한다.
        selectHalf(people, A, 0, N/2);

        // 결과를 출력한다.
        System.out.println(min);
    }

    // 팀을 둘로 나누는 method
    private static void selectHalf(int[] people, int[] A, int k, int N) {
        // k가 N에 도달하면, 팀의 점수를 합산해 최소의 값을 찾는다.
        if(k == N) {
            // people을 복사한 tmp 배열을 만들고, A팀에 속한 사람은 빼버린다.
            int[] tmp = people.clone();
            for (int i = 0; i < people.length; i++) {
                for (int j = 0; j < people.length/2; j++) {
                    if(tmp[i] == A[j]) {
                        tmp[i] = -1;
                    }
                }
            }
            // A팀과 B팀의 포인트 차이를 계산하여 min값과 비교해 더 작은 값을 min에 담는다.
            int answer = Math.abs(selectTwo(tmp) - selectTwo(A));
            min = min > answer? answer: min;
            return;
        }

        // 포인트는 항상 x, y와 y, x를 함께 더하기 때문에 x좌표와 y좌표가 바뀐 값은 동일한 값이다.
        // 따라서 입력되는 수는 항상 앞의 수보다 큰 수만 입력되도록 한다.
        for (int i = 0; i < people.length; i++) {
            if(k==0) {
                A[k] = i;
                selectHalf(people, A, k+1, N);
            }
            else if (A[k-1] < i) {
                A[k] = i;
                selectHalf(people, A, k+1, N);
            }
        }

    }

    // 주어진 배열에서 2개를 골라 Point 값을 더하는 method
    private static int selectTwo(int[] arr) {
        int answer = 0;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if(arr[i] != -1 && arr[j] != -1) {
                    answer += point[arr[i]][arr[j]] + point[arr[j]][arr[i]];
                }
            }
        }
        return answer;
    }

}
