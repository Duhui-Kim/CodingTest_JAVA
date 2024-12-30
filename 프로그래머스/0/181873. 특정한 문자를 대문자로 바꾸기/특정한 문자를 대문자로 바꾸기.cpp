#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string alp) {  
    for (char& s : my_string) {
        if (s == alp[0]) {
            s -= 32;
        }
    }
    return my_string;
}