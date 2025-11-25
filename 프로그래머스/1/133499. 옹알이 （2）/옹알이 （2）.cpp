#include <string>
#include <vector>
#include <iostream>

using namespace std;

bool compareString(string &s1, int s, int e, string &s2) {
    if (e - s > s2.size()) {
        return false;
    }
    
    int j=0;
    for (int i=s; i<e; i++, j++) {
        if (s1[i] != s2[j]) {
            return false;
        }
    }
    return true;
}

int solution(vector<string> babbling) {
    int answer = 0;
    string list[] = {"aya", "woo", "ye", "ma"};
    
    for (string babble : babbling) {
        int i=0;
        int cur = -1;
        bool check = true;
        while (i<babble.size() && check) {
            switch (babble[i]) {
                case 'a':
                    if (cur == 0) {
                        check = false;
                        break;
                    }
                    
                    check = compareString(babble, i, i+3, list[0]);
                    i += 3;
                    cur = 0;
                    break;
                case 'w':
                    if (cur == 1) {
                        check = false;
                        break;
                    }
                    
                    check = compareString(babble, i, i+3, list[1]);
                    i += 3;
                    cur = 1;
                    break;
                case 'y':
                    if (cur == 2) {
                        check = false;
                        break;
                    }
                    
                    check = compareString(babble, i, i+2, list[2]);
                    i += 2;
                    cur = 2;
                    break;
                case 'm':
                    if (cur == 3) {
                        check = false;
                        break;
                    }
                    
                    check = compareString(babble, i, i+2, list[3]);
                    i += 2;
                    cur = 3;
                    break;
                default:
                    check = false;
                    break;
            }
        }
        if (check) {
            answer++;
        }
    }
    
    return answer;
}