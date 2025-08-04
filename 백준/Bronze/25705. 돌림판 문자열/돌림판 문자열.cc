#include <iostream>
#include <string>
using namespace std;

int main() {
    int n, m;
    string s1, s2;
    cin >> n >> s1 >> m >> s2;

    int pos = 0;
    int count = 1;

    for (int j = 0; j < m; j++) {
        int step = 0;

        bool check = false;

        for (int k = 0; k < n; k++) {
            int cur = (pos + k) % n;
            if (s1[cur] == s2[j]) {
                pos = cur;
                count += k;
                check = true;

                if (j < m-1) {
                    pos++;
                    count++;
                }
                break;
            }
        }

        if (!check) {
            cout << -1 << endl;
            return 0;
        }
    }

    cout << count << endl;
    return 0;
}