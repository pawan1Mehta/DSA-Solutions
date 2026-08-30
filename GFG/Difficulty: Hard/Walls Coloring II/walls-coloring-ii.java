class Solution {
    int minCost(int[][] costs) {
        int n = costs.length;
        int m = costs[0].length;

        if (m == 1 && n > 1) return -1;

        int prevMin1 = Integer.MAX_VALUE, prevMin2 = Integer.MAX_VALUE, minIndex = -1;

        for (int j = 0; j < m; j++) {
            int val = costs[0][j];
            if (val < prevMin1) {
                prevMin2 = prevMin1;
                prevMin1 = val;
                minIndex = j;
            } else if (val < prevMin2) {
                prevMin2 = val;
            }
        }

        for (int i = 1; i < n; i++) {

            int currMin1 = Integer.MAX_VALUE, currMin2 = Integer.MAX_VALUE,
                currIndex = -1;

            for (int j = 0; j < m; j++) {

                int cost = costs[i][j] + (j == minIndex ? prevMin2 : prevMin1);

                if (cost < currMin1) {
                    currMin2 = currMin1;
                    currMin1 = cost;
                    currIndex = j;
                } else if (cost < currMin2) {
                    currMin2 = cost;
                }
            }

            prevMin1 = currMin1;
            prevMin2 = currMin2;
            minIndex = currIndex;
        }

        return prevMin1;
    }
}