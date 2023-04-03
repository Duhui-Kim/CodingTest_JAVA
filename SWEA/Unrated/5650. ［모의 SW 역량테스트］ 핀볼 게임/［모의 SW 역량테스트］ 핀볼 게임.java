import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    // 0 : 아래 / 1 : 왼쪽 / 2 : 위 / 3 : 오른쪽
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // testCase 입력받기
        int testCase = Integer.parseInt(sc.nextLine().trim());

        // testCase만큼 진행
        for (int tc = 1; tc <= testCase; tc++) {
            int N = Integer.parseInt(sc.nextLine().trim());

            // map을 저장할 배열과 웜홀 저장할 배열
            int[][] map = new int[N][N];
            int[][] warmHole = new int[12][4];
            for (int i = 6; i < 12; i++) {
                Arrays.fill(warmHole[i], -1);
            }

            // map 입력받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(sc.nextLine().trim());
                for (int j = 0; j < N; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    map[i][j] = num;

                    // 웜홀일 경우
                    if (num >= 6) {
                        // warmHole 좌표 저장 (0, 1이 한 쪽의 좌표, 2, 3이 다른 쪽의 좌표)
                        if (warmHole[num][0] == -1) {
                            warmHole[num][0] = i;
                            warmHole[num][1] = j;
                        } else {
                            warmHole[num][2] = i;
                            warmHole[num][3] = j;
                        }
                    }
                }
            }

            // 최대 튕긴 횟수
            int max = 0;

            // 맵 저장이 완료되었으면 맵 순회
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {

                    // 시작점 잡기
                    if (map[i][j] == 0) {
                        // 4 방향에 대해 진행
                        for (int k = 0; k < 4; k++) {
                            // 좌표와 방향 저장하고 시작
                            int nx = i;
                            int ny = j;
                            int dir = k;
                            int cnt = 0;

                            loop:
                            do {
                                // dir 방향으로 이동할 수 있는지 체크
                                int cx = nx + dx[dir];
                                int cy = ny + dy[dir];

                                // 범위 밖이면
                                if (cx < 0 || cy < 0 || cx >= N || cy >= N) {
                                    // 해당 위치에 올려놓고 시작
                                    nx = cx;
                                    ny = cy;
                                    dir = (dir + 2) % 4;
                                    cnt++;
                                }
                                // 블랙홀 만나면 종료
                                else if (map[cx][cy] == -1) break;

                                // 벽돌 만나면 방향 바꿈
                                else if (map[cx][cy] < 6 && map[cx][cy] > 0) {
                                    // 해당 위치에 올려놓고 시작
                                    nx = cx;
                                    ny = cy;
                                    cnt++;

                                    switch (dir) {
                                        // 아래로 갈 때
                                        case 0:
                                            if (map[nx][ny] == 1) {
                                                dir = 3;
                                            } else if (map[nx][ny] == 4) {
                                                dir = 1;
                                            } else {
                                                cnt *= 2;
                                                cnt--;
                                                break loop;
                                            }
                                            break;

                                        // 왼쪽으로 갈 때
                                        case 1:
                                            if (map[nx][ny] == 1) {
                                                dir = 2;
                                            } else if (map[nx][ny] == 2) {
                                                dir = 0;
                                            } else {
                                                cnt *= 2;
                                                cnt--;
                                                break loop;
                                            }
                                            break;

                                        // 위로 갈 때
                                        case 2:
                                            if (map[nx][ny] == 2) {
                                                dir = 3;
                                            } else if (map[nx][ny] == 3) {
                                                dir = 1;
                                            } else {
                                                cnt *= 2;
                                                cnt--;
                                                break loop;
                                            }
                                            break;

                                        // 오른쪽으로 갈 때
                                        case 3:
                                            if (map[nx][ny] == 4) {
                                                dir = 2;
                                            } else if (map[nx][ny] == 3) {
                                                dir = 0;
                                            } else {
                                                cnt *= 2;
                                                cnt--;
                                                break loop;
                                            }
                                            break;
                                    }
                                }
                                // 0이면 그 방향으로 이동
                                else if (map[cx][cy] == 0) {
                                    nx = cx;
                                    ny = cy;
                                }
                                // 웜홀 만나면 웜홀로 이동
                                else if (map[cx][cy] >= 6) {
                                    if (warmHole[map[cx][cy]][0] == cx && warmHole[map[cx][cy]][1] == cy) {
                                        nx = warmHole[map[cx][cy]][2];
                                        ny = warmHole[map[cx][cy]][3];
                                    } else {
                                        nx = warmHole[map[cx][cy]][0];
                                        ny = warmHole[map[cx][cy]][1];
                                    }
                                }
                            // 시작점에 도달하면 종료
                            } while (!(nx == i && ny == j));

                            // 튕긴 최댓값 저장
                            max = max < cnt ? cnt : max;
                        }
                    }
                }
            }
            sb.append(String.format("#%d %d\n", tc, max));
        }
        System.out.println(sb);
    }
}