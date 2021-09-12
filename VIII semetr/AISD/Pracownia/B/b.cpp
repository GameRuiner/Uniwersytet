#include <iostream>
#include <algorithm>
using namespace std;

int *a;
int h, mh, md, sum;
int n;

void maxHeight()
{
    int limit = sum + 1;
    int *dp1 = new int[limit];
    int *dp2 = new int[limit];
    int *dpGet, *dpSet;

    for (int j = 0; j < limit; j++)
    {
        dp1[j] = -2;
        dp2[j] = -2;
    }

    dp1[sum/2] = 0;
    for (int i = 1; i <= n; i++)
    {
        if (i % 2 == 1)
        {
            dpGet = dp1;
            dpSet = dp2;
        }
        else
        {
            dpGet = dp2;
            dpSet = dp1;
        }

        for (int j = 0; j < limit; j++)
        {
            if ((j - a[i - 1] >= 0 && dpGet[j - a[i - 1]] != -2))
            {
                dpSet[j] = max(dpSet[j], dpGet[j - a[i - 1]] + a[i - 1]);
            }
            if ((j + a[i - 1] < limit && dpGet[j + a[i - 1]] != -2))
            {
                dpSet[j] = max(dpSet[j], dpGet[j + a[i - 1]]);
            }
            if (dpGet[j] != -2)
            {
                dpSet[j] = max(dpSet[j], dpGet[j]);
            }
        }
    }
    h = dpSet[sum/2];
    if (h != 0)
    {
        cout << "TAK\n";
        cout << h;
    }
    else
    {
        int newsum = sum/2;
        cout << "NIE\n";
        for (int i = 0; i <= newsum; i++)
        {
            if ((dpSet[newsum+i] != 0 && dpSet[newsum-i] != 0) && (dpSet[newsum+i] != -2 && dpSet[newsum-i] != -2))
            {
                cout << i;
                return;
            }
        }
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
    sum = mh;
    mh /= 2;
    md = mh;
    maxHeight();
}
