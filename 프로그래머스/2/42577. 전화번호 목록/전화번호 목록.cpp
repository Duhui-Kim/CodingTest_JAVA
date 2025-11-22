#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

struct Edge {
    bool isEnd;
    unordered_map<char, Edge*> next;
    
    Edge() : isEnd(false) {}
};

bool insert(Edge &root, string &s) {

    Edge* cur = &root;
    for (char c : s) {
        if (cur->next.find(c) == cur->next.end()) {
            cur->next[c] = new Edge();
        }

        cur = cur->next[c];

        if (cur->isEnd) return false;
    }
    cur->isEnd = true;

    if (!cur->next.empty()) return false;
    return true;
}

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    Edge root;
    for (string number : phone_book) {
        answer = insert(root, number);
        
        if (!answer) {
            break;
        }
    }

    
    return answer;
}