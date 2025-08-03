#include <string>
#include <vector>
#include <iostream>

using namespace std;

int solution(string myString, string pat) {
    int answer = 0;
    
    int size = myString.size();
    int p_size = pat.size();
    for (int i=0, j=0; i<size; i++) {       
        if (myString[i] != pat[j]) {
            j++;
        } else {
            j=0;
        }
        
        if (j == pat.size()) {
            answer = 1;
            break;
        }
    }
    
    return answer;
}