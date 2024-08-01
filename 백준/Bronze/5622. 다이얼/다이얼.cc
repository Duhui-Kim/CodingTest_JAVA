#include <iostream>
using namespace std;

class Phone {
private:
				 // A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V,  W,  X,  Y,  Z
	int time[26] = {3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 8, 9, 9, 9, 10, 10, 10, 10};

public:
	int get(char name) {
		return time[name - 'A'];
	}
};

int main() {
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	Phone phone = Phone();
	string input;
	int answer = 0;

	cin >> input;

	for (int i = 0; i < input.length(); i++) {
		answer += phone.get(input.at(i));
	}

	cout << answer << endl;
}