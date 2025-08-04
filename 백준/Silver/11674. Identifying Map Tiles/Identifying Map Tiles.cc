#include <iostream>
#include <string>

using namespace std;

int main() {
    string s;
    cin >> s;

    int count = s.size();
    int x = 0;
    int y = 0;

    for (int i = 0; i < s.size(); i++) {
        x *= 2;
        y *= 2;
        
        switch (s[i])
        {
        case '0':
            break;
        case '1':
            x += 1;
            break;
        case '2':
            y += 1;
            break;
        case '3':
            x += 1;
            y += 1;
            break;
        default:
            break;
        }
    }

    cout << count << " " << x << " " << y << endl;

    return 0;
}