import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 숫자의 종류를 저장할 배열
        int[] num = new int[N+2];

        // 정렬 시 0이 앞으로 오지 않게 하기 위해 최대 값을 채워넣고 시작
        Arrays.fill(num, 2000);

        // 숫자의 개수를 세기 위한 배열
        int[] cnt = new int[1001];

        // 숫자를 입력받으며 cnt배열이 처음 올라갈 때 해당 숫자를 저장한다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        int idx = 0;
        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(st.nextToken());
            cnt[input]++;
            if (cnt[input] == 1) num[idx++] = input;
        }
        // 배열을 정렬
        Arrays.sort(num);

        StringBuilder sb = new StringBuilder();

        // 탐색은 0부터 idx 전까지 진행
        int i = 0;
        
        // num[i]가 2000이거나 i가 idx를 넘어가면 종료
        while (num[i] != 2000 && i < idx) {
            
            // 해당 숫자가 없으면 i를 증가시키고 다음 숫자를 찾는다.
            if (cnt[num[i]] == 0) {
                i++;
                continue;
            }

            // 다음 숫자가 지금 숫자보다 1 크지 않으면 지금 숫자 모두 출력
            if (num[i] + 1 != num[i + 1]) {
                while (cnt[num[i]] != 0) {
                    sb.append(num[i]).append(" ");
                    cnt[num[i]]--;
                }
            } 
            // 다음 숫자가 1 크긴 하지만 cnt가 없으면 지금 숫자 모두 출력
            else if (cnt[num[i+1]] == 0) {
                while (cnt[num[i]] != 0) {
                    sb.append(num[i]).append(" ");
                    cnt[num[i]]--;
                }
            } 
            // 다음 숫자가 1 크고 cnt도 있다면 i+2번째 이후에 숫자가 있는지 탐색
            else {
                boolean flag = false;
                int nidx = 0;
                for (int j = i+2; j < N; j++) {
                    if (num[j] == 2000) break;
                    if (cnt[num[j]] == 0) continue;
                    flag = true;
                    nidx = j;
                    break;
                }

                // i+2번째 이후의 숫자가 있다면 현재 숫자 모두 출력 후 해당 숫자 1번 출력
                if (flag) {
                    while (cnt[num[i]] != 0) {
                        sb.append(num[i]).append(" ");
                        cnt[num[i]]--;
                    }
                    sb.append(num[nidx]).append(" ");
                    cnt[num[nidx]]--;
                } 
                // i+2번째 이후의 숫자가 없다면 i+1번째 값 출력
                else {
                    while (cnt[num[i + 1]] != 0) {
                        sb.append(num[i + 1]).append(" ");
                        cnt[num[i + 1]]--;
                    }
                }
            }
        }

        // 출력
        System.out.println(sb);
    }
}