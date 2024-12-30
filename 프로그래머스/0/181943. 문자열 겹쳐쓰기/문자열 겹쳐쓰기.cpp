#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string overwrite_string, int s) {
    for (int i=s; i<my_string.size(); i++) {
        if (i - s >= overwrite_string.size()) break;
        
        my_string[i] = overwrite_string[i - s];
    }
    
    return my_string;
}