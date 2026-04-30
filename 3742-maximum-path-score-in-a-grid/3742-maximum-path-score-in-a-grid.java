class Solution {

    private int[][][] memo;

    private int maxPathScoreUtil(int i, int j, int k, int[][] grid) {
        if(!isValidMove(i, j, grid.length, grid[0].length) || k < 0) {
            return Integer.MIN_VALUE;
        }
        
        if(i == grid.length - 1 && j == grid[0].length - 1) {
            k = k - (grid[i][j] == 2 ? 1 : grid[i][j]);
            if(k < 0) {
                return Integer.MIN_VALUE;
            }
            return grid[i][j];
        }

        if(memo[i][j][k] != -1) {
            return memo[i][j][k];
        }

        int right = maxPathScoreUtil(i, j + 1, k - (grid[i][j] == 2 ? 1 : grid[i][j]), grid);
        int down = maxPathScoreUtil(i + 1, j, k - (grid[i][j] == 2 ? 1 : grid[i][j]), grid);

        int res;
        if(right == Integer.MIN_VALUE && down == Integer.MIN_VALUE) {
            res = Integer.MIN_VALUE;
        } else if(right == Integer.MIN_VALUE || down == Integer.MIN_VALUE) {
            res = (grid[i][j] + (right == Integer.MIN_VALUE ? down : right));
        } else {
            res = (grid[i][j] + Math.max(right, down));
        }

        return memo[i][j][k] = res;
    }

    private boolean isValidMove(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }

    public int maxPathScore(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        memo = new int[n][m][k + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        int res = maxPathScoreUtil(0, 0, k, grid);

        return res == Integer.MIN_VALUE ? -1 : res;
    }
}