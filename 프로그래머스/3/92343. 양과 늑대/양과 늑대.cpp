#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool visited[18][18][18] = {false,};
vector<int> node[18];
vector<int> kind;
int ans = 0;
void dfs(int x, int sheep, int wolf){
    ans = max(ans,sheep);

    for(int i = 0; i < node[x].size(); i++){
        int nx = node[x][i];

        if(kind[nx] == 0 && !visited[nx][sheep+1][wolf]){
            visited[nx][sheep+1][wolf] = true;
            kind[nx] = -1;
            dfs(nx,sheep+1,wolf);
            kind[nx] = 0;
            visited[nx][sheep+1][wolf] = false;
        }
        else if(kind[nx] == 1){
            if(sheep > wolf+1 && !visited[nx][sheep][wolf+1]){
                visited[nx][sheep][wolf+1] = true;
                kind[nx] = -1;
                dfs(nx,sheep,wolf+1);
                kind[nx] = 1;
                visited[nx][sheep][wolf+1] = false;
            }
        }
        else{
            if(!visited[nx][sheep][wolf]){
                visited[nx][sheep][wolf] = true;
                dfs(nx,sheep,wolf);
                visited[nx][sheep][wolf] = false;
            }
        }
    }
    
}
int solution(vector<int> info, vector<vector<int>> edges) {
    for(int i = 0; i < edges.size(); i++){
        node[edges[i][0]].push_back(edges[i][1]);
        node[edges[i][1]].push_back(edges[i][0]);
    }
    for(int i = 0; i < info.size(); i++) kind.push_back(info[i]);
    
    kind[0] = -1;
    visited[0][1][0] = true;
    dfs(0,1,0);
    return ans;
}