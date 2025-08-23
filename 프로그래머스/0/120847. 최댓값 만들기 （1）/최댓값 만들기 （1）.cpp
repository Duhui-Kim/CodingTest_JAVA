#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(int i, int j) {
    return i > j;
}

int solution(vector<int> numbers) {
    sort(numbers.begin(), numbers.end(), compare);
    
    return numbers[0] * numbers[1];
}