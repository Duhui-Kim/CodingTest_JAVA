#include <iostream>
using namespace std;

int posNum(int num) {
	int count = 1;

	while (num != 0) {
		num /= 10;
		count *= 10;
	}

	return count;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int a, b, c;
	cin >> a >> b >> c;

	cout << a + b - c << '\n';
	cout << a * posNum(b) + b - c << endl;
}