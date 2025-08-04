#include <string>
#include <vector>

using namespace std;

long long solution(int a, int b) {
    long long answer = 0;
    
    if (a > b) {
        int c = a;
        a = b;
        b = c;
    }
    
    while (a < b) {
        answer += a + b;
        a++;
        b--;
    }
    
    if (a == b) {
        answer += a;
    }
    
    
    return answer;
}