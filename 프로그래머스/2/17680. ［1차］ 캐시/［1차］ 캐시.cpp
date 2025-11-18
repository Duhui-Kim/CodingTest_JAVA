#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Cache {
    string s;
    int l;
    
    Cache(): s(""), l(0) {};
    Cache(string s, int last) : s(s), l(last) {};
    
    bool operator<(const Cache &other) {
        return l < other.l;
    }
};

int solution(int cacheSize, vector<string> cities) {
    if (cacheSize == 0) return 5 * cities.size();

    vector<Cache> v;
    int idx = 0, answer = 0;

    for (auto s : cities) {
        idx++;
        transform(s.begin(), s.end(), s.begin(), ::tolower);

        bool hit = false;
        for (auto& c : v) {
            if (c.s == s) {
                hit = true;
                c.l = idx;
                break;
            }
        }

        if (hit) {
            answer += 1;
        } else {
            answer += 5;
            if (v.size() < cacheSize) {
                v.push_back(Cache(s, idx));
            } else {
                sort(v.begin(), v.end());
                v[0] = Cache(s, idx);
            }
        }
    }

    return answer;
}
