#include<vector>
#include<queue>

using namespace std;

int solution(vector<vector<int>> maps)
{
    int max_x = maps.size();
    int max_y = maps[0].size();
    
    vector<vector<int>> road(max_x, vector<int>(max_y, -1));
    queue<pair<int, int>> q;
    q.push({0, 0});
    road[0][0] = 1;
    
    int dx[] = {0, 0, -1, 1};
    int dy[] = {1, -1, 0, 0};
    
    while (!q.empty()) {
        pair<int, int> p = q.front();
        q.pop();
        
        for (int i=0; i<4; i++) {
            int nx = p.first + dx[i];
            int ny = p.second + dy[i];
            
            if (nx < 0 || ny < 0 || nx >= max_x || ny >= max_y) continue;
            if (maps[nx][ny] == 0) continue;
            if (road[nx][ny] != -1) continue;
            
            road[nx][ny] = road[p.first][p.second] + 1;
            q.push({nx, ny});
        }
    }
    
    int answer = road[max_x-1][max_y-1];
    return answer;
}