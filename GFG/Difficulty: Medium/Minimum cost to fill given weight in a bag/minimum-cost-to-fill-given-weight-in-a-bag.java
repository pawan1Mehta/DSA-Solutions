class Solution {
    int[][] memo;
    
    private int minimumCostUtil(int i, int bagWeight, int[] cost) {
        if(bagWeight == 0) {
            return 0;
        }
        
        if(i == cost.length) {
            return Integer.MAX_VALUE;
        }
        
        if(memo[i][bagWeight] != -1) {
            return memo[i][bagWeight];
        }
        
        if(cost[i] == -1) {
            return minimumCostUtil(i + 1, bagWeight, cost);
        }
        
        int opt1 = Integer.MAX_VALUE;
        int opt2 = Integer.MAX_VALUE;
        
        if((bagWeight - (i + 1)) >= 0) {
            opt1 = minimumCostUtil(i, bagWeight - (i + 1), cost);
            if(opt1 != Integer.MAX_VALUE) {
                opt1 += cost[i];
            }
        }
        opt2 = minimumCostUtil(i + 1, bagWeight, cost);

        return memo[i][bagWeight] = Math.min(opt1, opt2);
    }
    
    public int minimumCost(int[] cost, int w) {
        int n = cost.length;
        
        memo = new int[n][w + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        int res = minimumCostUtil(0, w, cost);
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}