#include <string>
#include <vector>

using namespace std;

int solution(string myString, string pat) {
    int answer = 0;
    
    for (int i=0; i<=myString.size() - pat.size(); i++) {
        int idx = 0;
        bool complete = true;
        
        for (int j=i; j<i + pat.size(); j++) {
            if (myString[j] == pat[idx]) {
                idx++;
            } else {
                complete = false;
                break;
            }
        }
        
        if (complete) {
            answer++;
        }
    }
    
    return answer;
}