#include <iostream>
using namespace std;

int main() 
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int n, m;
	int basket[101] = { 0 };
	
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		basket[i] = i;
	}

	while (m-- > 0) {
		int i, j, tmp;
		cin >> i >> j;

		tmp = basket[i];
		basket[i] = basket[j];
		basket[j] = tmp;
	}

	for (int i = 1; i <= n; i++) {
		cout << basket[i] << ' ';
	}
	cout << endl;
}