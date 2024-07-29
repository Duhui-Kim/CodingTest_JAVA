#include <iostream>
using namespace std;

int main() {
	int n, m;
	int arr[101] = { 0 };

	cin >> n >> m;

	for (int i = 0; i < m; i++) {
		int s, e, k;
		cin >> s >> e >> k;

		for (int j = s; j <= e; j++) {
			arr[j] = k;
		}
	}
	
	for (int i = 1; i <= n; i++) {
		cout << arr[i] << ' ';
	}
	cout << endl;
}