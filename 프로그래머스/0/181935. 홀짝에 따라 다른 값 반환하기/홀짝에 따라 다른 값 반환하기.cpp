#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    vector<int> sum;

    sum.push_back(0);
    sum.push_back(1);
    sum.push_back(4);
    
    for (int i=3; i<=100; i++) {
        if (i % 2 == 0) {
            sum.push_back(sum[i-2] + (i*i));
        } else {
            sum.push_back(sum[i-2] + i);
        }
    }
    
    return sum[n];
}