#include <string>
#include <vector>

using namespace std;

int backtracking(vector<int> &numbers, int target, int cur, int idx, int max) {
    if (idx == max) {
        return cur == target ? 1 : 0;
    }
    
    return backtracking(numbers, target, cur + numbers[idx], idx + 1, max) 
        + backtracking(numbers, target, cur - numbers[idx], idx + 1, max);
}

int solution(vector<int> numbers, int target) {
    int answer = backtracking(numbers, target, 0, 0, numbers.size());
    return answer;
}