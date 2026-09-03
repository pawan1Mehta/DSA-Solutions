class Solution {
    private int getMaxDist(int[] bars, int maxBars) {
        int n = bars.length;

        Arrays.sort(bars);

        int maxWidth = 1;

        int lastVerticalBar = 1;
        int nextVerticalBar = lastVerticalBar + 1;
        
        for(int i = 0; i < n; i++) {
            if(nextVerticalBar != bars[i]) {
                maxWidth = Math.max(maxWidth, (nextVerticalBar - lastVerticalBar));

                lastVerticalBar = bars[i] - 1;
                nextVerticalBar = bars[i] + 1;
            } else {
                nextVerticalBar++;
            }
        }

        maxWidth = Math.max(maxWidth, nextVerticalBar - lastVerticalBar);

        return maxWidth;
    }

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxWidht = getMaxDist(vBars, m + 2);
        int maxHeight = getMaxDist(hBars, n + 2);

        int minWH = Math.min(maxWidht, maxHeight);

        return minWH * minWH;
    }
}