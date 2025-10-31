using namespace std;

long long solution(int price, int money, int count)
{
    long long answer = -1;
    long long total = price;
    total *= count * (count + 1) / 2;
    
    answer = total - money > 0 ? total - money : 0;

    return answer;
}