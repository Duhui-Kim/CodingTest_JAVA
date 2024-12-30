#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string alp) {
    string answer = "";
    
    for (char s : my_string) {
        if (s == '\0') break;
        
        if (s == alp[0]) {
            answer += (char) (s - 32);
        } else {
            answer += s;
        }
    }
    return answer;
}