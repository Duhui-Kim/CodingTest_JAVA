#include <string>
#include <vector>

using namespace std;

string solution(int n) {
    string answer = "";
    string c[2];
    c[0] = "수";
    c[1] = "박";
    
    int i = 0;
    while (i < n) {
        answer += c[i%2];
        i++;
    }
    
    return answer;
}