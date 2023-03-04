import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노트북의 가로와 세로
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] notebook = new int[N][M];

        // 스티커의 개수
        int K = Integer.parseInt(st.nextToken());

        // 출력할 결과
        int answer = 0;

        // 스티커 입력 반복문 시작
        for (int i = 0; i < K; i++) {

            st = new StringTokenizer(br.readLine());

            // 스티커의 가로와 세로
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];

            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
                    
            // 스티커 회전 반복문 시작 (0도 90도 180도 270도)
            loop:
            for (int j = 0; j < 4; j++) {

                // 행 열 중 스티커가 더 큰 값이 있으면 회전시킴
                if(N < R || M < C) {
                    sticker = rotate(sticker, R, C);
                    int tmp = R;
                    R = C;
                    C = tmp;
                    continue;
                }

                // 노트북에 대해 2중반복문을 돌리고 그 안에서 스티커 반복문
                for (int k = 0; k < N-R+1; k++) {
                    loop2:
                    for (int l = 0; l < M-C+1; l++) {
                        // 스티커 붙일 공간 있는지 체크
                        boolean isFitted = true;
                        for (int m = 0; m < R; m++) {
                            for (int n = 0; n < C; n++) {
                                // 스티커가 1인데 노트북에 이미 붙어있다면 다음 좌표로
                                if(sticker[m][n] == 1 && notebook[k+m][l+n] != 0) {
                                    isFitted = false;
                                    continue loop2;
                                }
                            }
                        }
                        // 스티커 공간이 있다면 거기에 붙이고 다음 스티커 찾으러 가기
                        if(isFitted) {
                            answer += attach(notebook, sticker, k, l, R, C);
                            break loop;
                        }
                    }
                }
                // 반복문이 끝난 것은 못 찾은 것이므로 90도 회전시키기
                sticker = rotate(sticker, R, C);
                int tmp = R;
                R = C;
                C = tmp;
            }
        }
        System.out.println(answer);
    }

    private static int attach(int[][] notebook, int[][] sticker, int n, int m, int r, int c){
        int cnt = 0;

        // 스티커의 값이 1일 때만 노트북에 붙여주면서 cnt 증가
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if(sticker[i][j] == 1) {
                    notebook[n+i][m+j] = sticker[i][j];
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 스티커 돌리는 method
    private static int[][] rotate(int[][] sticker, int r, int c) {
        int[][] tmp = new int[c][r];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                tmp[j][r-1-i] = sticker[i][j];
            }
        }
        return tmp;
    }
}