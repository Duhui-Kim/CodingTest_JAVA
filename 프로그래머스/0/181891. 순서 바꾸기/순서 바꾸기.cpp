#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list, int n) {
    vector<int> answer;
    
    if (n == num_list.size()) {
        n = 0;
    }

    for (int i=0; i<num_list.size(); i++) {
        answer.push_back(num_list[n++]);       
        
        if (n == num_list.size()) {
            n = 0;
        }
    }
    return answer;
}