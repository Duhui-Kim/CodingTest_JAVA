#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int n, size[6], t, p;

	cin >> n;
	for (int i = 0; i < 6; i++) {
		cin >> size[i];
	}
	cin >> t >> p;

	int tCount = 0;

	for (int i = 0; i < 6; i++) {
		tCount += size[i] / t;
		
		if (size[i] % t != 0) {
			tCount++;
		}
	}

	cout << tCount << '\n' << n / p << ' ' << n % p;
}