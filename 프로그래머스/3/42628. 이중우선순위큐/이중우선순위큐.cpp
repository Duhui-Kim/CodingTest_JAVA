#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> answer(2);
    map<int, int> map;

    for (string s : operations) {
        int cur = stoi(s.substr(2, sizeof(s)));
        if (s[0] == 'I') {
            map[cur]++;
        }
        else {
            if (map.empty()) continue;

            if (cur == 1) {
                auto it = --map.end();
                
                if (it->second == 1) {
                    map.erase(it->first);
                }
                else {
                    map[it->first]--;
                }
            }
            else {
                auto it = map.begin();

                if (it->second == 1) {
                    map.erase(it->first);
                }
                else {
                    map[it->first]--;
                }
            }
        }
    }

    if (!map.empty()) {
        auto it = map.begin();
        answer[1] = it->first;
        it = --map.end();
        answer[0] = it->first;
    }

    return answer;
}