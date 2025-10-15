#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string my_string, vector<int> indices) {
    sort(indices.begin(), indices.end());

    string answer = "";
    
    for (int i=0, j=0; i<my_string.size(); i++) {
        if (i == indices[j]) {
            j++;
            continue;
        }
        answer += my_string[i];
    }
    
    return answer;
}