#include <vector>
#include <set>

using namespace std;

int solution(vector<int> nums)
{
    set<int> s;
    for (int num : nums) {
        s.insert(num);
    }
    int max = nums.size() / 2;
    
    int answer = max > s.size() ? s.size() : max;
    return answer;
}