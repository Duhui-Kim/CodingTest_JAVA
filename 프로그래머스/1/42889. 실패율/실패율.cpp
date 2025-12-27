#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

struct stage {
    int num;
    int stay;
    int total;
    double rate;
    
    stage(int n, int s, int t) : num(n), stay(s), total(t), rate(0) {}

    void calculate() {
        if (total == 0) rate = 0;
        else rate = (double)stay / total;
    }
    
    bool operator<(const stage& other) const {
        if (rate == other.rate)
            return num < other.num;
        return rate > other.rate;
    }
};

vector<int> solution(int N, vector<int> stages) {
    vector<stage> stg;
    map<int, int> m;
    
    for (int s : stages) {
        m[s]++;
    }

    int total = stages.size();

    for (int i = 1; i <= N; i++) {
        int count = m[i];
        
        stage s(i, count, total);
        s.calculate();
        stg.push_back(s);
        
        total -= count;
    }
    
    sort(stg.begin(), stg.end());
    
    vector<int> answer;     
    for (auto s : stg) {
        answer.push_back(s.num);
    }
    return answer;
}