class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for(int j = 0; j < m; j++) {
            if(j > 0) {
                grid[0][j] += grid[0][j - 1]; 
            }

            if(grid[0][j] <= k) {
                count++;
            }
        }

        for(int i = 1; i < n; i++) {
            grid[i][0] += grid[i - 1][0];
            if(grid[i][0] <= k) {
                count++;
            }
        }


        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                grid[i][j] += grid[i - 1][j] + grid[i][j - 1] - grid[i - 1][j - 1];
                if(grid[i][j] <= k) {
                    count++;
                }
            }
        }

        return count;
    }
}