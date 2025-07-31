#include <string>
#include <vector>

using namespace std;

void bfs(int cur, int n, vector<vector<int>>& computers, vector<bool>& check) {   
    for (int i=0; i<n; i++) {
        if (computers[cur][i] == 0) continue;
        if (check[i]) continue;
        
        check[i] = true;
        bfs(i, n, computers, check);
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    vector<bool> check;
    
    for (int i=0; i<n; i++) {
        check.push_back(false);
    }
    
    for (int i=0; i<n; i++) {
        if (check[i]) continue;
        
        answer++;
        check[i] = true;
        bfs(i, n, computers, check);
    }
    
    return answer;
}