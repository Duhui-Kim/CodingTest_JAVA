#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> mats, vector<vector<string>> park) {
  
    vector<vector<int>> field;
    
    int maxLen = 0;
    for (int i=0; i<park.size(); i++) {
        vector<int> v;
        field.push_back(v);
        
        for (int j=0; j<park[0].size(); j++) {
            if (park[i][j] == "-1") {
                field[i].push_back(1);
                maxLen = 1;
            } else {
                field[i].push_back(0);
            }
        }
    }

    for (int i=1; i<field.size(); i++) {
        for (int j=1; j<field[0].size(); j++) {
            if (field[i][j] == 0) continue;
           
            field[i][j] = min({field[i - 1][j], field[i][j - 1], field[i - 1][j - 1]}) + 1;
            maxLen = max(maxLen, field[i][j]);
        }
    }
    
    int answer = -1;
    for (int num : mats) {
        if (num > maxLen) continue;
        
        answer = num > answer ? num : answer;
    }
    
    return answer;
}