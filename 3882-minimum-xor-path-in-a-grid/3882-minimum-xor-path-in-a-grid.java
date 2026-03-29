class Solution {
    private final int MAX_NUM = 1023;

    int[][][] memo;

    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }

    private int minCostUtil(int i, int j, int xorVal, int[][] grid, int n, int m) {
        if(i == n - 1 && j == m - 1) {
            return xorVal;
        }

        if(memo[i][j][xorVal] != -1) {
            return memo[i][j][xorVal];
        }

        int opt1 = Integer.MAX_VALUE;
        int opt2 = Integer.MAX_VALUE;
        
        if(j + 1 < m) {
            opt1 = minCostUtil(i, j + 1, xorVal ^ grid[i][j + 1], grid, n, m);
        }
        if(i + 1 < n) {
            opt2 = minCostUtil(i + 1, j, xorVal ^ grid[i + 1][j], grid, n, m);
        }

        return memo[i][j][xorVal] = Math.min(opt1, opt2);
    }

    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        memo = new int[n][m][MAX_NUM + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return minCostUtil(0, 0, grid[0][0], grid, grid.length, grid[0].length);
    }
}