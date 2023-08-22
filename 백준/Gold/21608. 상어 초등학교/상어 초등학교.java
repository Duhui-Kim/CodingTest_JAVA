import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] friends = new int[N*N+1][N*N+1];
        int[][] map = new int[N][N];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < N*N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cur = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                friends[cur][Integer.parseInt(st.nextToken())] = 1;
            }

            int[] location = new int[] {0, 0};
            int zeroCnt = -1;
            int favorite = -1;

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] != 0) continue;

                    int tmpZero = 0;
                    int tmpFavorite = 0;
                    for (int l = 0; l < 4; l++) {
                        int nx = j + dx[l];
                        int ny = k + dy[l];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                        if (map[nx][ny] == 0) {
                            tmpZero++;
                        } else if (friends[cur][map[nx][ny]] != 0) {
                            tmpFavorite++;
                        }
                    }

                    if (tmpFavorite > favorite) {
                        favorite = tmpFavorite;
                        zeroCnt = tmpZero;
                        location[0] = j;
                        location[1] = k;
                    } else if (tmpFavorite == favorite && tmpZero > zeroCnt) {
                        zeroCnt = tmpZero;
                        location[0] = j;
                        location[1] = k;
                    }
                }
            }
            map[location[0]][location[1]] = cur;
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    cnt += friends[map[i][j]][map[nx][ny]];
                }
                if (cnt == 0) continue;

                if (cnt == 1) {
                    answer += 1;
                } else if (cnt == 2) {
                    answer += 10;
                } else if (cnt == 3) {
                    answer += 100;
                } else {
                    answer += 1000;
                }
            }
        }
        System.out.println(answer);
    }
}