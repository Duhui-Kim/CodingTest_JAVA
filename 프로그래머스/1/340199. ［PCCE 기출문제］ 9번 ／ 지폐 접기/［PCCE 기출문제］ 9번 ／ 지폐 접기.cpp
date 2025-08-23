#include <string>
#include <vector>

using namespace std;

void swap(vector<int>& v) {
    if (v[0] < v[1]) {
        int tmp = v[1];
        v[1] = v[0];
        v[0] = tmp;
    }
}

int solution(vector<int> wallet, vector<int> bill) {
    int answer = 0;
    
    swap(wallet);
    swap(bill);
    
    while(bill[0] > wallet[0] || bill[1] > wallet[1]) {
        answer++;
        
        bill[0] /= 2;
        swap(bill);
    }
    
    return answer;
}