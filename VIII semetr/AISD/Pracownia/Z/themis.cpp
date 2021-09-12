// Marko Golovko
// 297354
// KPO
#include <iostream>
using namespace std;

int main() {

	int a, b;

	cin >> a >> b;

	if (a % 2021 != 0) {
		a = a - (a % 2021) + 2021;
	}

	for (int i = a; i <= b; i+=2021) {
		cout << i << " ";
	}
}	