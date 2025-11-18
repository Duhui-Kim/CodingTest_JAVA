#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    unordered_map<string, int> m;
    
    for (vector<string> v : clothes) {
        if (m.find(v[1]) == m.end()) {
            m[v[1]] = 1;
        }
        m[v[1]]++;
    }
    
    int answer = 1;
    for (auto a : m) {
        answer *= a.second;
    }
    
    return answer - 1;
}