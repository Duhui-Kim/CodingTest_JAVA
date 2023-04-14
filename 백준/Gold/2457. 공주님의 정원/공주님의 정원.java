import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    /*
        1. 시작일과 종료일을 모두 일로 변환
        2. 시작일이 낮은 순으로 정렬
        3. 처음에는 3월 1일보다 시작일이 작은 수 중에서 가장 종료일이 높은 수로 종료일을 업데이트
        4. 또 해당 종료일보다 시작일이 작은 수 중에서 종료일이 가장 큰 수를 저장
        5. 최종적으로 종료일이 11월 30일을 넘어가거나, queue가 비었을 경우 종료
        6. 11월 30일을 넘었을 경우 꽃의 개수 반환
        7. 안넘었을 경우 -1 반환
         */
    static class Month {
        int start, end;
        private Month(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {

        // 각 월별 누적일수 저장용
        int[] days = new int[12];
        days[1] = 31;
        days[2] = 28;
        days[3] = 31;
        days[4] = 30;
        days[5] = 31;
        days[6] = 30;
        days[7] = 31;
        days[8] = 31;
        days[9] = 30;
        days[10] = 31;
        days[11] = 30;

        for (int i = 1; i < 12; i++) {
            days[i] = days[i-1] + days[i];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 시작일 순으로 정렬
        PriorityQueue<Month> queue = new PriorityQueue<>((o1, o2) -> o1.start - o2.start);

        // 월 입력받기
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken());
            int end = days[Integer.parseInt(st.nextToken()) - 1] + Integer.parseInt(st.nextToken());

            queue.offer(new Month(start, end));
        }

        // 3월 1일부터 시작
        int start = days[2] + 1;

        // 임시로 저장할 end time
        int tmp = 0;

        // 종료일
        int end = days[11];
        
        // 꽃의 수
        int flower = 0;
        
        while(!queue.isEmpty()) {
            boolean select = false;
            
            int size = queue.size();
            flower++;
            for (int i = 0; i < size; i++) {
                Month month = queue.peek();
                // 시작지점 이전에 피는 꽃이 있다면
                if (month.start <= start) {
                    // 해당 꽃 제거
                    queue.poll();
                    select = true;
                    
                    // 그 꽃과 tmp를 비교하여 더 오래 피는 꽃의 값을 저장
                    tmp = tmp < month.end ? month.end : tmp;
                }
                // 시작지점을 벗어나면 지나감
                else {
                    break;
                }
            }            
            // tmp가 11월 31일을 넘어가면 종료
            if (tmp > end) break;
            // 아닌 경우 시작지점을 다시 tmp로 설정
            else start = tmp;

            // 안골라졌을 경우 종료
            if (!select) break;
        }

        if (tmp > end) {
            System.out.println(flower);
        } else {
            System.out.println(0);
        }
    }
}