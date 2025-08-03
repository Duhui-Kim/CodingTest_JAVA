#include <string>
#include <vector>

using namespace std;

int solution(int a, int b, int c) {
    int answer = 0;
    
    int num = a + b + c;
    int square = a * a + b * b + c * c;
    int cube =  a * a * a + b * b * b + c * c * c;
    
    if (a != b) {
        if (a != c && b != c) {
            answer = num;
        } else {
            answer = num * square;
        }
    } else {
        if (b == c) {
            answer = num * square * cube;
        } else {
            answer = num * square;
        }
    }
    
    return answer;
}