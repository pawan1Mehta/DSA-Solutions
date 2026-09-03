class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });

        int rmIntervals = 0;

        int lastEndTime = intervals[0][1];
        for(int i = 1; i < n; i++) {
            if(intervals[i][0] < lastEndTime) {
                rmIntervals++;
                lastEndTime = Math.min(lastEndTime, intervals[i][1]);
            } else {
                lastEndTime = intervals[i][1];
            }
        }

        return rmIntervals;
    }
}