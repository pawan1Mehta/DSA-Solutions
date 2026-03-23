class Solution {
    private final static int MOD = 1_000_000_007;
    
    private long maxVal;

    private void maxProductPathUtil(int i, int j, long val, int[][] grid) {
        if(i == grid.length - 1 && j == grid[0].length - 1) {
            maxVal = Math.max(maxVal, val);
            return;
        }

        if(grid[i][j] == 0) {
            maxVal = Math.max(maxVal, 0);
            return;
        }

        if(j + 1 < grid[0].length) {
            maxProductPathUtil(i, j + 1, val * grid[i][j + 1], grid);
        }

        if(i + 1 < grid.length) {
            maxProductPathUtil(i + 1, j, val * grid[i + 1][j], grid);
        }
    }

    public int maxProductPath(int[][] grid) {
        maxVal = -1;

        maxProductPathUtil(0, 0, grid[0][0], grid);

        return (int) (maxVal % MOD);  
    }
}