#include <string>
#include <vector>
#include <queue>
#include <functional>
#include <iostream>

using namespace std;

struct Edge {
    int num, x, y;
    Edge* left = nullptr;
    Edge* right = nullptr;
    
    Edge(int num, int x, int y) {
        this->num=num;
        this->x=x;
        this->y=y;
    }
    
    bool operator<(const Edge e) const {
        if (this->y == e.y) {
            return this->x < e.x;
        } else {
            return this->y > e.y;
        }
    }
};

void preorder(Edge* edge, vector<int>& result) {
    if (!edge) return;
    
    result.push_back(edge->num);
    preorder(edge->left, result);
    preorder(edge->right, result);
}

void postorder(Edge* edge, vector<int>& result) {
    if (!edge) return;
    
    postorder(edge->left, result);
    postorder(edge->right, result);
    result.push_back(edge->num);
}

void insert(Edge* parent, Edge* child) {
    if (parent->x > child->x) {
        if (!parent->left) {
            parent->left = child;
        } else {
            insert(parent->left, child);
        }
    } else {
        if (!parent->right) {
            parent->right = child;
        } else {
            insert(parent->right, child);
        }
    }
}

vector<vector<int>> solution(vector<vector<int>> nodeinfo) {
    vector<vector<int>> answer;
    
    vector<Edge> edges;
    int size = nodeinfo.size();
    
    for (int i=0; i<size; i++) {
        edges.push_back(Edge(i+1, nodeinfo[i][0], nodeinfo[i][1]));
    }
    
    sort(edges.begin(), edges.end());
    
    Edge* root = &edges[0];
    for (int i=1; i<size; i++) {
        insert(root, &edges[i]);
    }
    
    vector<int> preresult;
    vector<int> postresult;
    
    preorder(&edges[0], preresult);
    postorder(&edges[0], postresult);
    
    answer.push_back(preresult);
    answer.push_back(postresult);
    
    return answer;
}