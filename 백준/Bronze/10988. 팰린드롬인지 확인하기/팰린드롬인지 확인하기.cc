#include <iostream>
using namespace std;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	string word;
	cin >> word;

	bool isP = true;
	int j = word.length() - 1;

	for (int i = 0; i < word.length(); i++, j--) {
		if (i >= j) break;
		if (word[i] == word[j]) continue;

		isP = false;
	}

	if (isP) {
		cout << 1;
	}
	else {
		cout << 0;
	}
	cout << endl;
}