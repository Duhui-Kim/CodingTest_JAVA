#include <string>
#include <vector>
#include <queue>

using namespace std;

int make_binary(int n) {
    queue<int> q;
    
    while (n != 0) {
        q.push(n % 2);
        n /= 2;
    }
    
    int ret = 0;
    int ten = 1;
    while (!q.empty()) {
        ret += q.front() * ten;
        q.pop();
        ten *= 10;
    }
    return ret;
}

vector<int> solution(int l, int r) {
    vector<int> answer;
    
    for (int i=1; i<=128; i++) {
        int num = 5 * make_binary(i);
        if (num >= l && num <= r) {
            answer.push_back(num);
        } else if (num > r) {
            break;
        }
    }
    
    if (answer.empty()) {
        answer.push_back(-1);
    }
    
    return answer;
}