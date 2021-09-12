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

void maxHeight()
{
    int limit = 2 * sum + 1;
    int **dp = new int *[n + 1];
    for (int i = 0; i < n + 1; i++)
    {
        dp[i] = new int[limit];
    }

    for (int i = 0; i < n + 1; i++)
    {
        for (int j = 0; j < limit; j++)
        {
            dp[i][j] = INT_MIN;
        }
    }

    dp[0][sum] = 0;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j < limit; j++)
        {
            if ((j - a[i - 1] >= 0 && dp[i - 1][j - a[i - 1]] != INT_MIN))
            {
                dp[i][j] = max(dp[i][j], dp[i - 1][j - a[i - 1]] + a[i - 1]);
            }
            if ((j + a[i - 1] < limit && dp[i - 1][j + a[i - 1]] != INT_MIN))
            {
                dp[i][j] = max(dp[i][j], dp[i - 1][j + a[i - 1]]);
            }
            if (dp[i - 1][j] != INT_MIN)
            {
                dp[i][j] = max(dp[i][j], dp[i - 1][j]);
            }
            if (dp[i][j] != INT_MIN)
            {
            }
        }
    }
    h = dp[n][sum];

    //printAr(dp, n+1, limit);

    if (h != 0)
    {
        cout << "TAK\n";
        cout << h;
    }
    else
    {
        for (int i = 0; i < limit; i++)
        {
            if (dp[n][i] != 0)
            {
                cout << "NIE\n";
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
