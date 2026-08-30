class Solution {
    
    private int dfs(int row, int col, int[][] grid) {
        if(!isValid(row, col, grid.length, grid[0].length) 
            || grid[row][col] == Integer.MAX_VALUE) {
            return 0;       
        }    
        
        int count = 1;
        
        grid[row][col] = Integer.MAX_VALUE;
        
        count += dfs(row, col + 1, grid);
        count += dfs(row, col - 1, grid);
        count += dfs(row - 1, col, grid);
        count += dfs(row + 1, col, grid);
        
        return count;
    }
    
    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && col >= 0 && row < n && col < m;
    }
    
    public int largestArea(int n, int m, int k, int[][] arr) {
        int[][] grid = new int[n][m];
        
        int r, c;
        for(int i = 0; i < k; i++) {
            r = arr[i][0] - 1;
            c = arr[i][1] - 1;
            blockArea(grid, r, c);
        }
        
        int maxUnblockedArea = 0;
        
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                maxUnblockedArea = Math.max(maxUnblockedArea, dfs(i, j, grid));
            }
        }
        
        return maxUnblockedArea;
    }
    
    private void blockArea(int[][] grid, int r, int c) {
        int n = grid.length;
        int m = grid[0].length;
        
        for(int i = 0; i < n; i++) {
            grid[i][c] = Integer.MAX_VALUE;
        }
        
        for(int j = 0; j < m; j++) {
            grid[r][j] = Integer.MAX_VALUE;
        }
    }
}