#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int max = 1000001;
    
    vector<bool> isPrime(max, true);
    isPrime[0] = false;
    isPrime[1] = false;
    
    for (int i=2; i*i<max; i++) {
        if (!isPrime[i]) continue;
        
        for (int j=i*i; j<max; j+=i) {
            isPrime[j] = false;
        }
    }
    int answer = 0;

    for (int i=1; i<=n; i++) {
        if (isPrime[i]) answer++;
    }
    
    return answer;
}