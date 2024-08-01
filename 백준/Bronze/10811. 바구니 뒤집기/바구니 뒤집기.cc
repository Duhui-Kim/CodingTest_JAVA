#include <iostream>
using namespace std;

void swap(int a, int b, int arr[]) {
	int tmp = arr[a];
	arr[a] = arr[b];
	arr[b] = tmp;
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int n, m;
	int arr[101];

	cin >> n >> m;

	for (int i = 0; i <= n; i++) {
		arr[i] = i;
	}

	while (m-- > 0) {
		int s, e;
		cin >> s >> e;

		while (s < e) {
			swap(s, e, arr);
			
			s++; e--;
		}
	}

	for (int i = 1; i <= n; i++) {
		cout << arr[i] << ' ';
	}
	cout << endl;
}