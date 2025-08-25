#include <string>
#include <vector>

using namespace std;

int solution(string str1, string str2) {  
    for (int i=0; i<str2.size(); i++) {
        bool check = true;
        
        for (int j=0; j<str1.size(); j++) {
            if (i + j >= str2.size() || str1[j] != str2[i + j]) {
                check = false;
                break;
            }
        }
        
        if (check) return 1;
    }
    
    return 0;
}