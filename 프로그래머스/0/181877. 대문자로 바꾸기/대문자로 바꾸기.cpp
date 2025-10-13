#include <string>
#include <vector>

using namespace std;

string solution(string myString) {
    string answer = "";
    int gap = 'A' - 'a';
    
    for (char c : myString) {
        if (c >= 'a' && c <= 'z') {
            answer += c + gap;
        } else {
            answer += c;
        }
    }
    
    return answer;
}