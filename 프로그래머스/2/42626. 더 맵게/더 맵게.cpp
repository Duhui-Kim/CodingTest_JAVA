#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    priority_queue<int, vector<int>, greater<int>> q;
    
    for (int i=0; i<scoville.size(); i++) {
        q.push(scoville[i]);
    }
        
    while (q.top() < K) {
        answer++;
        int a = q.top();
        q.pop();
        
        if (q.empty()) {
            answer = -1;
            break;
        }
        
        int b = q.top();
        q.pop();
        
        q.push(a + b * 2);
    }
      
    return answer;
}