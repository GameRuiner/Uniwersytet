#include <iostream>
#include <utility>
#include <vector>
#include <algorithm>
using namespace std;


int binarySearch(int l, int r, long long x);
vector <pair<long long, long long>> pairs;

int main() {
    int m;
	long long d, n;
    cin >> m;
    pair <long,long> d_n;
    pairs.resize(m);              
    for (int i = 0; i < m; i++) {
        cin >> d;
        cin >> n;
        d_n.first = d;
        d_n.second = n;
        pairs[i] = d_n;
    }
    sort(pairs.begin(), pairs.end());
    int pairs_number = 0;
    for (int i = 0; i < m;) {
        if (pairs[i].second == 0) {
            i+=1;
        } else if (!(pairs[i].second % 2)) {
            //pairs[i].second = pairs[i].second >> 1;
            //pairs[i].first = pairs[i].first << 1;
            pairs[i].second /= 2;
            pairs[i].first *= 2;
            int start = i+1;
            int end = pairs.size() - 1;
            int new_pos = binarySearch(start, end, pairs[i].first); 
            if (new_pos != -1) {
                pairs[new_pos].second += pairs[i].second;
                i+=1;
            }
        } else if (pairs[i].second % 2) {
            pairs[i].second-=1;
            pairs_number+=1;
        }
    }
    cout << pairs_number;
}

int binarySearch(int l, int r, long long x) 
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