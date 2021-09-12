#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;


int binarySearch(int l, int r, long long int x);
static vector <pair<long long int, long long int>> pairs;

int main() {
    int m;
	long long int d, n;
    cin >> m;
    for (int i = 0; i < m; i ++) {
        cin >> d;
        cin >> n;
        pairs.push_back(make_pair(d,n));
    }
    sort(pairs.begin(), pairs.end());
    long long int pairs_number = 0;
    for (int i = 0; i < m;) {
        if (pairs[i].second % 2 != 0) {
            pairs[i].second -= 1;
            pairs_number += 1;
        }
        if (pairs[i].second == 0) {
            i += 1;
        } else {
            pairs[i].second /= 2;
            pairs[i].first *= 2;
            int start = i+1;
            int end = pairs.size() - 1;
            int new_pos = binarySearch(start, end, pairs[i].first); 
            if (new_pos != -1) {
                pairs[new_pos].second += pairs[i].second;
                i += 1;
            }
        }
    }
    cout << pairs_number;
}

int binarySearch(int l, int r, long long int x) 
{ 
    if (r >= l) { 
        int mid = l + (r - l) / 2; 
        if (pairs[mid].first == x) 
            return mid; 
        if (pairs[mid].first > x) 
            return binarySearch(l, mid - 1, x); 
        return binarySearch(mid + 1, r, x); 
    } 
    return -1; 
} 