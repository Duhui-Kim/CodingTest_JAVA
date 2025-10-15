#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    
    for (int num : num_list) {
        while (num > 1) {
            num /= 2;
            answer += 1;            
        }
    }
    
    return answer;
}