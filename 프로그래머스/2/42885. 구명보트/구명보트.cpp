#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> people, int limit) {
    int answer = people.size();
    sort(people.begin(), people.end());
    
    int l = 0;
    int r = people.size() - 1;
    
    while (l < r) {
        if (people[l] + people[r] <= limit) {
            answer--;
            l++;
            r--;
        } else {
            r--;
        }
    }
    
    return answer;
}