#include <iostream>
using namespace std;

int main() 
{
	ios::sync_with_stdio(false);
	cin.tie(0);
	cout.tie(0);

	int tc;
	cin >> tc;

	while (tc-- > 0) {
		int p1[2], p2[2] = { 0 };
		int starCount;
		int answer = 0;

		cin >> p1[0] >> p1[1] >> p2[0] >> p2[1] >> starCount;

		while (starCount-- > 0) {
			int star[3] = { 0 };

			cin >> star[0] >> star[1] >> star[2];

			int d1 = (star[0] - p1[0]) * (star[0] - p1[0])
				+ (star[1] - p1[1]) * (star[1] - p1[1]);

			int d2 = (star[0] - p2[0]) * (star[0] - p2[0])
				+ (star[1] - p2[1]) * (star[1] - p2[1]);

			int r = star[2] * star[2];

			if ((d1 < r && r < d2) || (d2 < r && r < d1)) {
				answer++;
			}
		}
		cout << answer << '\n';
	}
	cout << endl;
}