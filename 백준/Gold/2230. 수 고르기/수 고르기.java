import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 리스트에 숫자를 입력받기
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        // 정렬하기 - O(NlogN)
        Collections.sort(list);

        // 투포인터로 값 찾기
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;

        // 검색 시간을 줄이기 위해 end < N이고 두 수의 차이가 M보다 작을 때 먼저 end를 증가시킨다.
        while (end < N && list.get(end) - list.get(start) < M) end++;
        
        // end가 N보다 작을 때만 비교
        // 1. 두 수의 차이가 M보다 클 경우 min값과 비교해서 min에 값을 넣고
        // 2. 차이가 M보다 작을 경우 start만 증가 (오름차순으로 정렬되어있으므로)
        // 3. 차이가 M과 같을 경우에는 더 작은 수는 없으므로 종료
        while(end < N){
            if(list.get(end) - list.get(start) > M){
                min = Math.min(min, list.get(end) - list.get(start));
                start++;
            } else if (list.get(end) - list.get(start) < M) {
                end++;
            } else {
                min = M;
                break;
            }
            if(end == N || start > end) break;
        }
        // min값 출력
        System.out.println(min);
    }
}
