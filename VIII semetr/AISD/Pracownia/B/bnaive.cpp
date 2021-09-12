#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;

int *a;
int h, mh, md;
int n;

void maxHeight(int x, int y, int i)
{
    if (x == y && x != 0 && x > h)
    {
        h = x;
    }
    if (x > mh || y > mh)
    {
        return;
    }
    if (md < x-y) {
        md = x-y;
    }
    if (i < n)
    {
        maxHeight(x + a[i], y, i + 1);
        maxHeight(x, y + a[i], i + 1);
        maxHeight(x, y, i + 1);
    }
}

int main()
{
    int d;
    mh = 0;
    h = 0;
    cin >> n;
    a = new int[n];
    for (int i = 0; i < n; i++)
    {
        cin >> d;
        a[i] = d;
        mh += d;
    }

    mh /= 2;
    md = mh;
    maxHeight(0, 0, 0);

    if (h != 0)
    {
        cout << "TAK\n";
        cout << h;
    } else {
        cout << "NIE\n";
        cout << md;
    }
}
