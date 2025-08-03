#include <string>
#include <vector>

using namespace std;

int euclid(int a, int b) {
    if (b == 0) return a;
    return euclid(b, a%b);
}

vector<int> solution(int n, int m) {
    vector<int> answer;
    
    int num = 0;
    if (n > m) {
        num = euclid(n, m);
    } else {
        num = euclid(m, n);
    }
    
    answer.push_back(num);
    answer.push_back(((long) n * m) / num);
    
    return answer;
}