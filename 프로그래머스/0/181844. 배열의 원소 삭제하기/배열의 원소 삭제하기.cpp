#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> arr, vector<int> delete_list) {
    vector<int> answer;
    
    sort(delete_list.begin(), delete_list.end());
    
    for (int a : arr) {
        if (binary_search(delete_list.begin(), delete_list.end(), a) == 0) {
            answer.push_back(a);
        }
    }
    
    return answer;
}