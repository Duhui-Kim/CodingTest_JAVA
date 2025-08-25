#include <string>
#include <vector>

using namespace std;

long long solution(long long n) {  
    long long answer = 0;
    
    long long arr[10] = {0, };
    
    while(n > 0) {
        arr[n % 10]++;
        n /= 10;
    }
    
    long long pos = 1;
    for (int i=0; i<10; i++) {       
        while (arr[i] > 0) {           
            answer += pos * i;
            pos *= 10;
            arr[i]--;
        }
    }
    
    return answer;
}