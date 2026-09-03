class Solution {
public:
    int removeCoveredIntervals(vector<vector<int>>& intervals) {
        int n = intervals.size();

        sort(intervals.begin(), intervals.end(), [](const auto& a, const auto& b){
            if(a[0] == b[0]) {
                return a[1] > b[1];
            }
            return a[0] < b[0];
        });

        int count = 0;
        auto& temp = intervals[0];

        for(int i = 1; i < n; i++) {
            if(isCovered(temp, intervals[i])) {
                count++;
            } else {
                temp = intervals[i];
            }
        }

        return n - count;
    }

    bool isCovered(vector<int> interval2, vector<int> interval1) {
        int a = interval1[0];
        int b = interval1[1];
        int c = interval2[0];
        int d = interval2[1];
        return c <= a && b <= d;
    }
};



















