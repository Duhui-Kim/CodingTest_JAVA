#include <string>
#include <vector>

using namespace std;

string solution(string myString, string pat) {
    string answer = "";
    
    for (int i=myString.length()-1; i>=0; i--) {
        bool check = true;
        for (int j=pat.length()-1; j>=0; j--) {
            if (myString[i + j - pat.length() + 1] != pat[j]) {
                check = false;
                break;
            }
        }
        if (check) {
            for (int j=0; j<=i; j++) {
                answer += myString[j];
            }
            break;
        }
    }
    
    return answer;
}