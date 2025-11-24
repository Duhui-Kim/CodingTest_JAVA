#include <vector>
#include <iostream>
using namespace std;

int solution(vector<int> nums) {
    int max = 3000;
    
    vector<bool> isPrime(max + 1, true);
    isPrime[0] = false;
    isPrime[1] = false;

    for (int i=2; i*i<=max; i++) {
        if (!isPrime[i]) continue;
        
        for (int j=i*i; j<=max; j+=i) {
            isPrime[j] = false;
        }
    }

    int answer = 0;
    int n = nums.size();
    for (int i=0; i<n; i++) {
        for (int j=i+1; j<n; j++) {
            for (int k=j+1; k<n; k++) {
                if (isPrime[nums[i] + nums[j] + nums[k]]) {
                    answer++;
                }
            }
        }
    }
    
    return answer;
}