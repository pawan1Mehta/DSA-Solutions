class Solution {
    
    private void dfs(int i, int j, int[][] grid) {
        if(!isValidMove(i, j, grid.length, grid[0].length) || grid[i][j] == 0) {
            return;
        }    
        
        grid[i][j] = 0;
        
        dfs(i, j + 1, grid);
        dfs(i, j - 1, grid);
        dfs(i - 1, j, grid);
        dfs(i + 1, j, grid);
    }
    
    private boolean isValidMove(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }
    
    int cntOnes(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(i == 0 || j == 0 || i == n-1 || j == m-1) {
                    if(grid[i][j] == 1) {
                        dfs(i, j, grid);
                    }
                }
            }
        }
        
        int count = 0;
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1) {
                    count++;
                }
            } 
        }
        
        return count;
    }
};