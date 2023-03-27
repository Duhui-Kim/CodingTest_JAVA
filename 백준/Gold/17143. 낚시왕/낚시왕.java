import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int R;
    private static int C;
    private static int M;
    private static Shark[] sharks;
    private static boolean[] check;
    private static int answer;
    // 상어 객체 생성
    static class Shark {
        int r, c, s, d, z, idx;
        Shark(int r, int c, int s, int d, int z, int idx) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 격자판 크기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 상어 개수
        M = Integer.parseInt(st.nextToken());

        // 상어 저장용 배열
        sharks = new Shark[M+1];

        // 배열에 상어 입력받기
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // 2*(R-1) 또는 2*(C-1)마다 제자리로 돌아오므로 나머지로 한다.
            if(d == 1 || d ==2) {
                s %= 2 * (R-1);
            } else {
                s %= 2 * (C-1);
            }

            // shark 객체를 만들어서 sharks 배열에 넣는다.
            Shark shark = new Shark(r, c, s, d, z, i);

            sharks[i] = shark;
        }

        answer = 0;

        for (int i = 1; i < M+1; i++) {
            int[][] check = new int[R][C];

            int tmpR = sharks[i].r;
            int tmpC = sharks[i].c;

            // 같은 좌표에 상어가 있을 경우 작은 상어 잡아먹힌다.
            if(check[tmpR][tmpC] == 0) {
                check[tmpR][tmpC] = sharks[i].idx;
            } else {
                if (sharks[check[tmpR][tmpC]].z >= sharks[i].z) {
                    sharks[i] = null;
                } else {
                    sharks[check[tmpR][tmpC]] = null;
                    check[tmpR][tmpC] = sharks[i].idx;
                }
            }
        }

        // 낚시꾼 움직여서 상어 잡음 -> 상어 움직임 반복
        for (int i = 0; i <= C; i++) {
            findShark(i);
            moveShark();
        }
        System.out.println(answer);
    }

    // 상어 움직이기 method
    private static void moveShark() {

        int[][] check = new int[R][C];

        for (int i = 1; i < M+1; i++) {
            if(sharks[i] == null) continue;
            int move = sharks[i].s;
            boolean isIncrease = false;

            // 방향에 따라서 설정
            switch (sharks[i].d) {
                // 위
                case 1:
                    while (move > 0) {
                        if(sharks[i].r == 0) {
                            isIncrease = true;
                            sharks[i].d = 2;
                        }
                        if(sharks[i].r == R-1) {
                            isIncrease = false;
                            sharks[i].d = 1;
                        }
                        move--;
                        if (!isIncrease) sharks[i].r--;
                        else sharks[i].r++;
                    }

                    break;
                // 아래
                case 2:
                    isIncrease = true;
                    while (move > 0) {
                        if(sharks[i].r == 0) {
                            isIncrease = true;
                            sharks[i].d = 2;
                        }
                        if(sharks[i].r == R-1) {
                            isIncrease = false;
                            sharks[i].d = 1;
                        }
                        move--;
                        if (!isIncrease) sharks[i].r--;
                        else sharks[i].r++;
                    }

                    break;
                // 오른쪽
                case 3:
                    isIncrease = true;
                    while (move > 0) {
                        if(sharks[i].c == 0) {
                            isIncrease = true;
                            sharks[i].d = 3;
                        }
                        if(sharks[i].c == C-1) {
                            isIncrease = false;
                            sharks[i].d = 4;
                        }
                        move--;
                        if (!isIncrease) sharks[i].c--;
                        else sharks[i].c++;
                    }

                    break;
                // 왼쪽
                case 4:
                    while (move > 0) {
                        if(sharks[i].c == 0) {
                            isIncrease = true;
                            sharks[i].d = 3;
                        }
                        if(sharks[i].c == C-1) {
                            isIncrease = false;
                            sharks[i].d = 4;
                        }
                        move--;
                        if (!isIncrease) sharks[i].c--;
                        else sharks[i].c++;
                    }
                    break;
            }
            int tmpR = sharks[i].r;
            int tmpC = sharks[i].c;

            // 같은 좌표에 상어가 있을 경우 작은 상어 잡아먹힌다.
            if(check[tmpR][tmpC] == 0) {
                check[tmpR][tmpC] = sharks[i].idx;
            } else {
                if (sharks[check[tmpR][tmpC]].z >= sharks[i].z) {
                    sharks[i] = null;
                } else {
                    sharks[check[tmpR][tmpC]] = null;
                    check[tmpR][tmpC] = sharks[i].idx;
                }
            }
        }
    }

    // 상어잡는 method
    private static void findShark(int i) {
        Shark[] arr = new Shark[R];

        // 해당 열에 있는 상어를 모두 arr에 담는데, row 순서대로 저장한다.
        for (int j = 1; j < M+1; j++) {
            if(sharks[j] == null) continue;
            if(sharks[j].c == i) {
                arr[sharks[j].r] = sharks[j];
            }
        }

        for (int j = 0; j < R; j++) {
            // 가장 위쪽에 있는 상어를 잡는다. 잡고나서 해당 shark는 null로 바꾼다.
            if(arr[j] != null) {
                answer += arr[j].z;
                sharks[arr[j].idx] = null;
                return;
            }
        }
        return;
    }
}