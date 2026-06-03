class Solution {

    private int solve(
        int[] start1,
        int[] duration1,
        int[] start2,
        int[] duration2
    ) {
        int n = start1.length;
        int m = start2.length;

        int minDist1 = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            minDist1 = Math.min(minDist1, start1[i] + duration1[i]);
        }

        int minDist2 = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            minDist2 = Math.min(
                minDist2,
                Math.max(start2[i], minDist1) + duration2[i]
            );
        }

        return minDist2;
    }

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int min1 = solve(landStartTime, landDuration, waterStartTime, waterDuration);
        int min2 = solve(waterStartTime, waterDuration, landStartTime, landDuration);
        return Math.min(min1, min2);
    }
}