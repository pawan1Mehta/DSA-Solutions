class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = neededTime.length;

        int minTime = 0;
        int i = 0, j = 0;

        while(i < n && j < n) {
            int totalTime = 0;
            int maxTime = 0;

            while(j < n && colors.charAt(i) == colors.charAt(j)) {
                totalTime += neededTime[j];
                maxTime = Math.max(maxTime, neededTime[j]);
                j++;
            }

            minTime += totalTime - maxTime;

            i = j;
        }

        return minTime;
    }
}