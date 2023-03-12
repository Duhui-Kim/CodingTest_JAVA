import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static class Cur {
        int r;
        int c;
        public Cur(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 사방탐색용
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int N = Integer.parseInt(br.readLine());

        // N * N 크기의 배열 생성
        int[][] school = new int[N][N];

        // 좋아하는 학생 배열 생성
        int[][] prefer = new int[N*N+1][4];

        // 점수 집계용 배열
        int[] point = {0, 1, 10, 100, 1000};

        // N * N만큼 반복문 진행
        for (int t = 0; t < N * N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 학생 번호를 입력받고, 해당 배열에 선호하는 학생도 입력받음
            int k = Integer.parseInt(st.nextToken());
            for (int i = 0; i < 4; i++) {
                prefer[k][i] = Integer.parseInt(st.nextToken());
            }

            int maxcnt = -1;
            int maxbnk = -1;

            Cur cur = new Cur(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // 비어있는 칸과 만나면 체크를 시작
                    if (school[i][j] == 0) {
                        int cnt = 0;
                        int bnk = 0;

                        // 사방탐색 진행
                        for (int m = 0; m < 4; m++) {
                            int nr = i + dr[m];
                            int nc = j + dc[m];
                            // 사방 탐색을 진행하며 좋아하는 학생 수와 공백 체크
                            // 공백이면 다음 좌표로 넘어감
                            // 좋아하는 학생이 있으면 학생 cnt를 증가시키고 다른 좌표로 넘어감.
                            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                            if (school[nr][nc] == 0) {
                                bnk++;
                            } else {
                                for (int l = 0; l < 4; l++) {
                                    if (school[nr][nc] == prefer[k][l]) {
                                        cnt++;
                                        break;
                                    }
                                }
                            }

                            // 학생 수의 카운트한 값이 현재 최댓값보다 크면 업데이트해주고, 그 떄의 좌표를 저장
                            // 같으면 bnk값을 비교해서 좌표 값 업데이트
                            if (cnt > maxcnt) {
                                maxcnt = cnt;
                                maxbnk = bnk;
                                cur.r = i;
                                cur.c = j;
                            } else if (cnt == maxcnt) {
                                if (bnk > maxbnk) {
                                    maxbnk = bnk;
                                    cur.r = i;
                                    cur.c = j;
                                }
                            }
                        }
                    }
                }
            }
            // 최댓값이 입력된 곳에 학생번호 입력
            school[cur.r][cur.c] = k;
        }
        int answer = 0;

        // 각 좌표에 대해서 사방탐색을 진행하여, 선호하는 학생 수를 count
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                    // 일치하는 값을 찾았으면 다음 값을 찾기 위해 break
                    for (int l = 0; l < 4; l++) {
                        if(school[nr][nc] == prefer[school[i][j]][l]) {
                            cnt++;
                            break;
                        }
                    }
                }
                // cnt된 숫자에 맞는 포인트를 answer에 더해준다.
                answer += point[cnt];
            }
        }

        System.out.println(answer);
    }
}
