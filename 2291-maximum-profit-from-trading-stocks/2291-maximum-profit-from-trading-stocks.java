class Solution {

    int[][] memo;
    
    private int maximumProfitUtil(int i, int budget, int[] present, int[] future) {
        if(i >= present.length) {
            return 0;
        }

        if(memo[i][budget] != -1) {
            return memo[i][budget];
        }

        int opt1 = 0, opt2 = 0;

        if(present[i] <= budget) {
            opt1 = (future[i] - present[i]) + maximumProfitUtil(i + 1, budget - present[i], present, future);
        }
        
        opt2 = maximumProfitUtil(i + 1, budget, present, future);

        return memo[i][budget] = Math.max(opt1, opt2);
    }

    public int maximumProfit(int[] present, int[] future, int budget) {
        int n = present.length;

        memo = new int[n][budget + 1];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        return maximumProfitUtil(0, budget, present, future);
    }
}