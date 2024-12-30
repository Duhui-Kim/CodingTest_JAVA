#include <string>
#include <vector>

using namespace std;

vector<string> solution(vector<string> strArr) {   
    int flag = (char) ('Z' + 1);
    
    for (int i=0; i<strArr.size(); i++) {
        if (i%2 == 0) {
            for (char& s : strArr[i]) {
                if (s < flag) {
                    s += 32;
                }
            }
            
        } else {
            for (char& s : strArr[i]) {
                if (s > flag) {
                    s -= 32;
                }
            }
        }
    }
    
    return strArr;
}