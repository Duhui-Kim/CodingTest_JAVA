#include <string>
#include <vector>

using namespace std;

int solution(int n, int m, vector<int> section) {   
    int answer = 0;
    
    int cur = 0;
    for (int a : section) {
        if (a <= cur) continue;
        
        answer++;
        cur = a + m - 1;
    }
    
    return answer;
}