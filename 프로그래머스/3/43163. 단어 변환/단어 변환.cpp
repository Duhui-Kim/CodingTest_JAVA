#include <string>
#include <vector>
#include <iostream>

using namespace std;

int answer;

int diffCount(string &a, string &b) {
    int idx = 0;
    int count = 0;
    
    for (char c : a) {
        if (c != b[idx++]) {
            count++;
        }
    }

    return count;
}

void solve(string cur, string target, vector<string> &words, bool* check, int count) {
    if (diffCount(cur, target) == 0) {
        answer = min(answer, count);
        return;
    }
    
    int size = words.size();
    for (int i=0; i<size; i++) {
        if (check[i]) continue;
        
        string s = words[i];
        if (diffCount(cur, s) == 1) {
            check[i] = true;
            solve(s, target, words, check, count + 1);
            check[i] = false;
        }
    }
}

int solution(string begin, string target, vector<string> words) {
    bool check[51] {};    
    answer = 99999999;    
    solve(begin, target, words, check, 0);
    return answer == 99999999 ? 0 : answer;
}