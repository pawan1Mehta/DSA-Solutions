class Solution {
    public static int MOD = 1_000_000_007;

    private int numberOfPathsUtil(int i, int j, int remainder, int[][] grid, int k, int[][][] memo) {
        if(i == grid.length || j == grid[0].length) {
            return 0;
        }

        if(i == grid.length - 1 && j == grid[0].length - 1) {
            return (remainder + grid[i][j]) % k == 0 ? 1 : 0;
        }

        if(memo[i][j][remainder] != -1) {
            return memo[i][j][remainder];
        }

        int opt1 = numberOfPathsUtil(i, j + 1, (remainder + grid[i][j]) % k, grid, k, memo);
        int opt2 = numberOfPathsUtil(i + 1, j, (remainder + grid[i][j]) % k, grid, k, memo);
        
        return memo[i][j][remainder] = (opt1 + opt2) % MOD;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
    
        int[][][] memo = new int[n][m][k];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return numberOfPathsUtil(0, 0, 0, grid, k, memo);
    }
}