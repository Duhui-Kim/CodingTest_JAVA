import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int maxNum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        StringTokenizer st;

        // map 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 방향별 백트래킹 진행
        for (int i = 0; i < 4; i++) {
            backTracking(map, N, i, 0);
        }

        System.out.println(maxNum);
    }

    private static void backTracking(int[][] map, int N, int dir, int k) {
        if(k == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    maxNum = Math.max(maxNum, map[i][j]);
                }
            }
            return;
        }

        // 새롭게 합쳐질 배열
        int[][] tmp = new int[N][N];

        // 방향에 따라 합산
        switch (dir) {
            case 0:
                // 왼쪽으로 합치고 정렬하기
                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    int tmpNum = 0;
                    int j = 0;
                    while (j < N) {
                        if(map[i][j] != 0) {
                            if(map[i][j] == tmpNum) {
                                tmp[i][idx-1] = tmpNum * 2;
                                tmpNum = 0;
                            } else {
                                tmp[i][idx++] = map[i][j];
                                tmpNum = map[i][j];
                            }
                        }
                        j++;
                    }
                }
                break;
            case 1:
                // 오른쪽으로 합치고 정렬하기
                for (int i = 0; i < N; i++) {
                    int idx = N-1;
                    int tmpNum = 0;
                    int j = N-1;
                    while (j >= 0) {
                        if(map[i][j] != 0) {
                            if(map[i][j] == tmpNum) {
                                tmp[i][idx+1] = tmpNum * 2;
                                tmpNum = 0;
                            } else {
                                tmp[i][idx--] = map[i][j];
                                tmpNum = map[i][j];
                            }
                        }
                        j--;
                    }
                }
                break;
            case 2:
                // 위쪽으로 합치고 정렬하기
                for (int i = 0; i < N; i++) {
                    int idx = 0;
                    int tmpNum = 0;
                    int j = 0;
                    while (j < N) {
                        if(map[j][i] != 0) {
                            if(map[j][i] == tmpNum) {
                                tmp[idx-1][i] = tmpNum * 2;
                                tmpNum = 0;
                            } else {
                                tmp[idx++][i] = map[j][i];
                                tmpNum = map[j][i];
                            }
                        }
                        j++;
                    }
                }
                break;
            case 3:
                // 아래쪽으로 합치고 정렬하기
                for (int i = 0; i < N; i++) {
                    int idx = N-1;
                    int tmpNum = 0;
                    int j = N-1;
                    while (j >= 0) {
                        if(map[j][i] != 0) {
                            if(map[j][i] == tmpNum) {
                                tmp[idx+1][i] = tmpNum * 2;
                                tmpNum = 0;
                            } else {
                                tmp[idx--][i] = map[j][i];
                                tmpNum = map[j][i];
                            }
                        }
                        j--;
                    }
                }
                break;
        }

        // 각 방향별 백트래킹
        for (int i = 0; i < 4; i++) {
            backTracking(tmp, N, i, k+1);
        }

    }
}