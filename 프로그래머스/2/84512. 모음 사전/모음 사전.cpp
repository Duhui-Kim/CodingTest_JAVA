#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(string word) {
    unordered_map<char, int> map;
    map['A'] = 0;
    map['E'] = 1;
    map['I'] = 2;
    map['O'] = 3;
    map['U'] = 4;
    
    int answer = 0;
    
    int nums = 781;
    for (char c : word) {
        answer += 1;

        if (c != 'A') {
            answer += map[c] * nums;
        }
        
        nums = (nums - 1) / 5;
    }
    
    return answer;
}