import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 스케쥴링 진행할 arr 배열
        int[] arr = new int[K];
        // 남은 수 체크용 remain 배열
        int[] remain = new int[101];
        // 선택한 애들 담을 배열
        int[] select = new int[N];

        st = new StringTokenizer(br.readLine());
        
        // arr 입력받기
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            remain[arr[i]]++;
        }

        int idx = 0;
        int cnt = 0;
        
        // 반복문 진행
        for (int i = 0; i < K; i++) {
            // 멀티탭을 다 안 꽂았을 때
            if (idx < N) {
                // 안꽂혀있는애가 들어오면 꽂아준다.
                if (!check(arr[i], select)) {
                    select[idx++] = arr[i];
                }
            // 다 꽂혀있을 때
            } else {
                // 이미 꽂혀있는애가 아니면
                if (!check(arr[i], select)) {
                    cnt++;

                    // 꽂혀있는 애들 중에 안 쓸 애가 있으면 대체한다.
                    boolean removed = false;
                    for(int j=0; j<idx; j++) {
                        if (remain[select[j]] == 0) {
                            select[j] = arr[i];
                            removed = true;
                            break;
                        }
                    }

                    // 다 쓰는 애들이면 가장 뒤쪽에 등장하는 애를 빼준다.
                    if (!removed) {
                        boolean[] count = new boolean[N];
                        int chk = 0;
                        loop:
                        for (int j = i+1; j < K; j++) {
                            for (int k = 0; k < N; k++) {
                                if (count[k]) continue;

                                if (select[k] == arr[j]) {
                                    count[k] = true;
                                    chk++;
                                    if (chk == N) {
                                        select[k] = arr[i];
                                        break loop;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // 하나를 꽂았으니까 걔의 값을 1 줄여준다.
            remain[arr[i]]--;
        }
        System.out.println(cnt);
    }

    // 안에 있는지 유무 체크용
    private static boolean check(int num, int[] select) {
        for (int i = 0; i < select.length; i++) {
            if (num == select[i]) {
                return true;
            }
        }
        return false;
    }
}