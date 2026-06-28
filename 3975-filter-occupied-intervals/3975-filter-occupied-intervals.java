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
        return b[0] <= a[1] + 1;
    }
    
    public List<List<Integer>> filterOccupiedIntervals(int[][] occupiedIntervals, int freeStart, int freeEnd) {
        int[][] mergedIntervals = merge(occupiedIntervals);

        int n = mergedIntervals.length;

        List<List<Integer>> res = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            int left = mergedIntervals[i][0];
            int right = mergedIntervals[i][1];
            if(right < freeStart || left > freeEnd) {
                res.add(Arrays.asList(left, right));
            } else if(left >= freeStart && right <= freeEnd) {
                continue;
            } else if(left < freeStart && right > freeEnd) {
                res.add(Arrays.asList(left, freeStart - 1));
                res.add(Arrays.asList(freeEnd + 1, right));
            } else if(left < freeStart) {
                res.add(Arrays.asList(left, freeStart - 1));
            } else {
                res.add(Arrays.asList(freeEnd + 1, right));
            }
        }
        
        return res;
    }
}