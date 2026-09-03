class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                if(a[1] == b[1]) {
                    return Integer.compare(b[0], a[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        });

        int count = 0;
        int[] currInterval = new int[]{-1, -1};

        int a, b;
        for(int[] interval : intervals) {
            a = interval[0];
            b = interval[1];

            if(currInterval[1] < a) {
                currInterval[0] = b -1;
                currInterval[1] = b;
                count += 2;
            } else if(currInterval[0] < a) {
                currInterval[0] = currInterval[1];
                currInterval[1] = b;
                count += 1;
            }
        }

        return count;
    }
}