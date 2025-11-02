#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> rank, vector<bool> attendance) {
    int answer = 0;
    vector<pair<int, int>> s;
    for (int i=0; i<rank.size(); i++) {
        s.push_back(make_pair(rank[i], i));
    }
    sort(s.begin(), s.end());
    
    int num = 10000;
    for (auto p : s) {
        if (attendance[p.second]) {
            answer += p.second * num;
            num /= 100;
        }
        
        if (num == 0) break;
    }
    
    return answer;
}