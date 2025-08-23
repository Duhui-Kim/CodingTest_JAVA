#include <string>
#include <vector>
using namespace std;

int solution(int n, int w, int num) {
    // num 의 위치 찾기
    int row = (num - 1) / w;
    int col = (num - 1) % w;
    int real_col = (row % 2 == 0) ? col : (w - 1 - col);

    int answer = 0;
    // num 위쪽 행부터 끝까지 확인
    for (int r = row; r * w < n; r++) {
        int idx;
        if (r % 2 == 0) {
            idx = r * w + real_col + 1;
        } else {
            idx = r * w + (w - real_col);
        }
        if (idx <= n) answer++;
    }

    return answer;
}