#include <string>
#include <vector>

using namespace std;

string solution(string myString) {
    int size = myString.length();
    string answer;
    
    for (int i=0; i<size; i++) {
        if (myString[i] - 'a' < 0) {
            answer += (myString[i] - 'A' + 'a');
        } else {
            answer += myString[i];
        }
    }

    return answer;
}