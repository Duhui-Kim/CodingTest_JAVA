#include <string>
#include <vector>

using namespace std;

void dfs(vector<vector<int>> &tree, vector<pair<int, int>> &pairs, vector<int> &sales, int cur) {
    if (tree[cur].empty()) {
        pairs[cur].first = sales[cur-1];
        return;
    }
    
    int sum_min = 0;
    int gap_min = 1000000000;
    for (int next : tree[cur]) {
        dfs(tree, pairs, sales, next);
        
        int gap = pairs[next].first - pairs[next].second;
        gap_min = gap_min > gap ? gap : gap_min;
        sum_min += pairs[next].first > pairs[next].second ? pairs[next].second : pairs[next].first;
    }
    
    pairs[cur].first = sales[cur-1] + sum_min;
    
    if (gap_min < 0) {
        pairs[cur].second = sum_min;
    } else {
        pairs[cur].second = gap_min + sum_min;
    }
}

int solution(vector<int> sales, vector<vector<int>> links) {
    vector<vector<int>> tree(sales.size()+1, vector<int>());
    vector<pair<int, int>> pairs(sales.size()+1, pair<int, int>());

    for (vector<int> link : links) {
        tree[link[0]].push_back(link[1]);
    }
    
    dfs(tree, pairs, sales, 1);
        
    int answer = pairs[1].first > pairs[1].second ? pairs[1].second : pairs[1].first;
    
    return answer;
}