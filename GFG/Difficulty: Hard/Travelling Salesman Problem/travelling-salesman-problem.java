class Solution {
    public int[][] memo;
    
    private int travellingSalesmanProblem(int currCity, int mask, int[][] cost, int n) {
        if(mask == ((1 << n) - 1)) {
            return cost[currCity][0];
        }
        
        if(memo[currCity][mask] != -1) {
            return memo[currCity][mask];
        }
        
        int minCost = Integer.MAX_VALUE;
        
        for(int nextCity = 0; nextCity < n; nextCity++) {
            if((mask & (1 << nextCity)) == 0) {
                minCost = Math.min(
                    minCost, 
                    cost[currCity][nextCity] + travellingSalesmanProblem(nextCity, mask | (1 << nextCity), cost, n)
                );
            }
        }
        
        return memo[currCity][mask] = minCost;
    }
    
    public int tsp(int[][] cost) {
        int n = cost.length;
        
        memo = new int[n][(1 << 16)];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return travellingSalesmanProblem(0, 1, cost, n);
    }
}