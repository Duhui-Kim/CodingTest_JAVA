#include <string>
#include <vector>

using namespace std;

bool check(string& s1, string& s2) {
    int size1 = s1.size();
    int size2 = s2.size();
    
    for (int i=0, j=0; i<size1; i++) {
        if (s1[i] == s2[j]) {
            j++;
        } else {
            j = 0;
        }
        
        if (j == size2) {
            return true;
        }
    }

    return false;
}

string solution(vector<string> str_list, string ex) {
    string answer = "";
    
    for (string s : str_list) {
        if (!check(s, ex)) {
            answer += s;
        }
    }
    
    return answer;
}