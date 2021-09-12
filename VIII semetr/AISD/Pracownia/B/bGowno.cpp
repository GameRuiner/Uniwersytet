#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
#include <climits>
#include <queue>
using namespace std;

static vector<int> a;
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
    int *dp2;
    //int *dpSet;

    for (int j = 0; j < limit; j++)
    {
        dp1[j] = INT_MIN;
        //dp2[j] = INT_MIN;
    }

    dp1[sum] = 0;
    queue<pair<int, int>> pairs;
    int offset = limit + 1;
    for (int i = 1; i <= n; i++)
    {
        for (int j = 0; j < limit; j++)
        {
            int newJ = INT_MIN;
            int jminusa, jplusa, prostoj;
            if (j - a[i - 1] >= 0 && j - a[i - 1] >= offset)
            {
                jminusa = dp2[j - a[i - 1] - offset];
                if (j >= offset)
                {
                    prostoj = dp2[j - offset];
                    if (j + a[i - 1] < limit && j + a[i - 1] >= offset)
                    {
                        jplusa = dp2[j + a[i - 1] - offset];
                    }
                }
            }
            else
            {
                prostoj = dp1[j];
                if (prostoj != INT_MIN)
                {
                    newJ = max(newJ, prostoj);
                }
                if (j - a[i - 1] >= 0)
                {
                    jminusa = dp1[j - a[i - 1]];
                    if (jminusa != INT_MIN)
                    {
                        newJ = max(newJ, jminusa + a[i - 1]);
                    }
                }
                if (j + a[i - 1] < limit)
                {
                    jplusa = dp1[j + a[i - 1]];
                    if (jminusa != INT_MIN)
                    {
                        newJ = max(newJ, jplusa);
                    }
                }
            }
            pairs.push(make_pair(j, newJ));
            while (!pairs.empty() && pairs.front().first < (j - a[i - 1]))
            {
                dp1[pairs.front().first] = pairs.front().second;
                pairs.pop();
            }
        }
        int k = pairs.size();
        dp2 = new int[k];
        offset = pairs.front().first;
        int onemorei = 0;
        while (!pairs.empty())
        {
            dp2[0] = pairs.front().second;
            pairs.pop();
            onemorei++;
        }
    }

    while (!pairs.empty())
    {
        dp1[pairs.front().first] = pairs.front().second;
        // if (pairs.front().first >= sum) {
        //     break;
        // }
        pairs.pop();
    }
    h = dp1[sum];

    //printAr(dp1, limit);
    //printAr(dpSet, limit);

    if (h != 0)
    {
        cout << "TAK\n";
        cout << h;
    }
    else
    {
        cout << "NIE\n";
        if (n == 2)
        {
            cout << abs(a[0] - a[1]);
        }
        else
            for (int i = 0; i < limit; i++)
            {
                if (dp1[i] != 0)
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
    //a = new int[n];
    for (int i = 0; i < n; i++)
    {
        cin >> d;
        a.push_back(d);
        mh += d;
    }
    sort(a.begin(), a.end(), greater<int>());
    sum = mh;
    mh /= 2;
    md = mh;
    maxHeight();
}
