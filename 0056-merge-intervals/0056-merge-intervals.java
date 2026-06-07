class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;

        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[0] == b[0]) {
                    return Integer.compare(a[1], b[1]);
                }
                return Integer.compare(a[0], b[0]);
            }
        });

        ArrayList<int[]> res = new ArrayList<>();

        int[] curr = intervals[0];
        for(int i = 1; i < n; i++) {
            if(isOverlaping(curr, intervals[i])) {
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                res.add(curr);
                curr = intervals[i];
            }
        }

        res.add(curr);

        int[][] resArr = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }

        return resArr;
    }

    private boolean isOverlaping(int[] a, int[] b) {
        return b[0] <= a[1] ? true : false;
    }
}