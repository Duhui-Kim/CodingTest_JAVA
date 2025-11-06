#include <string>
#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int getSort(vector<int> arr, int a, int b, int c) {   
    sort(arr.begin() + a - 1, arr.begin() + b);

    return arr[a + c - 2];
}

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for (vector<int> com : commands) {
        answer.push_back(getSort(array, com[0], com[1], com[2]));
    }
    
    return answer;
}