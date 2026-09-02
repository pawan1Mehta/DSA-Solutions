class Solution {
    public int minCost(int n) {
        if(n <= 2) {
            return n <= 1 ? 0 : 1;
        }

        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;

        for(int k = 3; k <= n; k++) {
            int minCost = Integer.MAX_VALUE;
            for(int num = 1; num < k; num++) {
                int cost = num * (k - num);

                int costNum1 = dp[num];
                int costNum2 = dp[k - num];

                minCost = Math.min(minCost, cost + costNum1 + costNum2);
            }

            dp[k] = minCost;
        }

        return dp[n];
    }
}