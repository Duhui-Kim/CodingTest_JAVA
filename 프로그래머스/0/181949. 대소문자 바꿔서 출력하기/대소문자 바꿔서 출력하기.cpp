#include <iostream>
#include <string>

using namespace std;

int main(void) {
    string str;
    cin >> str;
    
    for (int i=0; i<str.length(); i++) {
        if (str[i] < (char) ('Z' + 1)) {
            cout << (char) (str[i] + 32);
        } else {
            cout << (char) (str[i] - 32);
        }
    }
    return 0;
}