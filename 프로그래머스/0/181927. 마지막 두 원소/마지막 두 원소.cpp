#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> answer;
    
    int size = num_list.size();
    
    for (int a : num_list) {
        answer.push_back(a);
    }
    
    if (num_list[size-1] <= num_list[size-2]) {
        answer.push_back(num_list[size-1] * 2);
    } else {
        answer.push_back(num_list[size-1] - num_list[size-2]);
    }
    
    return answer;
}