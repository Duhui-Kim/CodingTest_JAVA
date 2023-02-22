import java.util.*;

public class Solution {
    private static int N;
    private static int M;
    private static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        테케 입력받기
        int testCase = sc.nextInt();

        for(int t=1; t<=testCase; t++) {

            N = sc.nextInt();
            M = sc.nextInt();

            // arr의 0번째와 N-1번째 값은 하얀색과 빨간색으로 칠해줌
            int[] arr = new int[N];
            arr[0] = 1;
            arr[N - 1] = 3;

            // 최댓값으로 초기화
            answer = Integer.MAX_VALUE;

            // flag에 입력받기
            char[][] flag = new char[N][M];
            for (int i = 0; i < N; i++) {
                flag[i] = sc.next().toCharArray();
            }

            // 백트래킹
            findMin(flag, arr, 1);
            
            // 결과 출력
            System.out.printf("#%d %d\n", t, answer);
        }
    }

    private static void findMin(char[][] flag, int[] arr, int K) {

        // 기저 조건
        if(K > N-2){
            // 항상 파란색이 있어야하므로 중간값은 2 이상이어야 한다.
            if(arr[N-2] >= 2) {
                int cnt = 0;

                // arr에 있는 값으로 칠해준다.
                for(int i=0; i<N; i++){
                    int color = arr[i];
                    for(int j=0; j<M; j++){
                        switch (color){
                            // 1일 경우 흰색으로 칠해줌
                            case 1:
                                if(flag[i][j] != 'W') cnt++;
                                break;
                            // 2일 경우 파란색으로 칠해줌
                            case 2:
                                if(flag[i][j] != 'B') cnt++;
                                break;
                            // 3일 경우 빨간색으로 칠해줌
                            case 3:
                                if(flag[i][j] != 'R') cnt++;
                                break;
                        }
                    }
                }
                if(answer > cnt) answer = cnt;
            }
            return;
        }

        // 재귀 조건
        // 항상 하얀색, 파란색, 빨간색 순서이므로 이전보다 큰 수가 들어가야한다.
        for(int i=1; i<=3; i++){
            if(arr[K-1] <= i){
                arr[K] = i;
                findMin(flag, arr, K+1);
            }
        }
    }
}
