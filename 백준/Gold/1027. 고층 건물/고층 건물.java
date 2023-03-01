import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 빌딩의 개수 N을 입력받고,
        int N = Integer.parseInt(br.readLine());

        // building의 높이를 담을 double 배열을 만든다.
        double[] building = new double[N];

        // 소수점 계산도 필요하기 때문에 double로 building의 정보를 받는다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            building[i] = Double.parseDouble(st.nextToken());
        }

        // max를 0으로 두고 각 building에 대해 checkBuilding method를 실행한다.
        int max = 0;
        for (int i = 0; i < N; i++) {
            // -1은 왼쪽 체크, 1은 오른쪽 체크
            int cnt = checkBuilding(building, -1, i, N);
            cnt += checkBuilding(building, 1, i, N);
            max = Math.max(cnt, max);
        }

        // 결과를 출력
        System.out.println(max);
    }
    
    // 해당 방향의 보이는 건물 수를 체크하는 method
    private static int checkBuilding(double[] building, int dir, int idx, int N) {
        // 중간의 가장 높은 건물의 높이를 0, 그 index를 현재 idx - 1로 설정 (zerodivision 막기 위해)
        double midHeight = 0;
        int midIdx = idx - 1;
        
        // 빌딩의 수를 세기 위한 cnt 선언
        int cnt = 0;

        // 1부터 N까지 진행하는데, 이 때 index가 범위 밖으로 넘어가면 종료
        for (int i = 1; i < N; i++) {
            if(idx + dir * i < 0 || idx + dir * i >= N) {
                break;
            }
            
            // 기울기 : 중간의 가장 높은 건물과 현재 건물 높이 차이 / 중간건물과 현재건물 idx 차이
            // compare = 현재건물의 높이 - 기울기 * 현재건물과 타겟건물의 idx 차이
            double compare = building[idx] - Math.abs(dir * i) * (building[idx] - midHeight) / Math.abs(idx - midIdx);
            
            // compare보다 높은 건물이 있다면 해당 건물로 가장 큰 건물을 바꿔주고, cnt를 올린다.
            if(building[idx + dir * i] > compare) {
                midHeight = building[idx + dir * i];
                midIdx = idx + dir * i;
                cnt++;
            }
        }
        return cnt;
    }
}