#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <climits>
using namespace std;

int *a;
int h, mh, md, sum;
int n;

void printAr(int **arr, int rows, int cols)
{

    for (int i = 0; i < rows; i++)
    {
        for (int j = 0; j < cols; j++)
        {
            cout << " " << arr[i][j] << " ";
        }
        cout << "\n";
    }
}

void printAr(int *arr, int cols)
{
    for (int j = 0; j < cols; j++)
    {
        cout << arr[j] << " ";
    }
    cout << "\n";
}

void maxHeight()
{
    int limit = 2 * sum + 1;
    int *dp1 = new int[limit];
    int *dp2 = new int[limit];
    int *dpGet, *dpSet;

    for (int j = 0; j < limit; j++)
    {
        dp1[j] = INT_MIN;
        dp2[j] = INT_MIN;
    }

    dp1[sum] = 0;
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
            if ((j - a[i - 1] >= 0 && dpGet[j - a[i - 1]] != INT_MIN))
            {
                dpSet[j] = max(dpSet[j], dpGet[j - a[i - 1]] + a[i - 1]);
            }
            if ((j + a[i - 1] < limit && dpGet[j + a[i - 1]] != INT_MIN))
            {
                dpSet[j] = max(dpSet[j], dpGet[j + a[i - 1]]);
            }
            if (dpGet[j] != INT_MIN)
            {
                dpSet[j] = max(dpSet[j], dpGet[j]);
            }
        }
    }
    h = dpSet[sum];

    //printAr(dpGet, limit);
    //printAr(dpSet, limit);

    if (h != 0)
    {
        cout << "TAK\n";
        cout << h;
    }
    else
    {
        cout << "NIE\n";
        for (int i = 0; i < sum; i++)
        {
            if ((dpSet[sum+i] != 0 && dpSet[sum-i] != 0) && (dpSet[sum+i] != INT_MIN && dpSet[sum-i] != INT_MIN))
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
