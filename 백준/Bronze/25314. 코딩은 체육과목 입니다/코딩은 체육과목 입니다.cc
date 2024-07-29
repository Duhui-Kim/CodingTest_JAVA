#include <iostream>
using namespace std;

int main() 
{
	int N;
	cin >> N;

	int repeat = N / 4;

	for (int i = 0; i < repeat; i++) 
	{
		cout << "long ";
	}
	cout << "int";
}