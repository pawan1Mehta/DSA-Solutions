class Solution {
    
    private int[][] validStree1Moves = new int[][]{
        {1, 4, 6},
        {1, 3, 5}
    };

    private int[][] validStree2Moves = new int[][]{
        {2, 3, 4},
        {2, 5, 6}
    };

    private int[][] validStree3Moves = new int[][]{
        {1, 4, 6},
        {2, 5, 6}
    };

    private int[][] validStree4Moves = new int[][]{
        {1, 5, 3},
        {2, 5, 6}
    };

    private int[][] validStree5Moves = new int[][]{
        {1, 4, 6},
        {2, 3, 4}
    };

    private int[][] validStree6Moves = new int[][]{
        {1, 3, 5},
        {2, 3, 4}
    };

    boolean[][] visited;

    private boolean hasValidPath(int i, int j, int[][] grid) {
        if(i == grid.length - 1 && j == grid[0].length - 1) {
            return true;
        }

        if(visited[i][j]) {
            return false;
        }

        visited[i][j] = true;

        boolean res = false;

        if(grid[i][j] == 1) {
            if(isValidStree1Left(i, j - 1, grid)) {
                res = hasValidPath(i, j - 1, grid);
            }
            if(isValidStree1Right(i, j + 1, grid)) {
                res = res || hasValidPath(i, j + 1, grid);
            }
        } else if(grid[i][j] == 2) {
            if(isValidStree2Up(i - 1, j, grid)) {
                res = hasValidPath(i - 1, j, grid);
            }
            if(isValidStree2Down(i + 1, j, grid)) {
                res = res || hasValidPath(i + 1, j, grid);
            }
        } else if(grid[i][j] == 3) {
            if(isValidStree3Left(i, j - 1, grid)) {
                res = hasValidPath(i, j - 1, grid);
            }
            if(isValidStree3Down(i + 1, j, grid)) {
                res = res || hasValidPath(i + 1, j, grid);
            }
        } else if(grid[i][j] == 4) {
            if(isValidStree4Right(i, j + 1, grid)) {
                res = hasValidPath(i, j + 1, grid);
            }
            if(isValidStree4Down(i + 1, j, grid)) {
                res = res || hasValidPath(i + 1, j, grid);
            }
        } else if(grid[i][j] == 5) {
            if(isValidStree5Left(i, j - 1, grid)) {
                res = hasValidPath(i, j - 1, grid);
            }
            if(isValidStree5Up(i - 1, j, grid)) {
                res = res || hasValidPath(i - 1, j, grid);
            }
        } else {
            if(isValidStree6Right(i, j + 1, grid)) {
                res = hasValidPath(i, j + 1, grid);
            }
            if(isValidStree6Up(i - 1, j, grid)) {
                res = res || hasValidPath(i - 1, j, grid);
            }
        }
        
        return res;
    }

    private boolean isValidStree1Left(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree1Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree1Right(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree1Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree2Up(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree2Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree2Down(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree2Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree3Left(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree3Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree3Down(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree3Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree4Right(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree4Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree4Down(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree4Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree5Left(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree5Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree5Up(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree5Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree6Right(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree6Moves[0]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }

    private boolean isValidStree6Up(int i, int j, int[][] grid) {
        if(!isValidStep(i, j, grid.length, grid[0].length)) {
            return false;
        }
        
        for(int val : validStree6Moves[1]) {
            if(grid[i][j] == val) {
                return true;
            }
        }

        return false;
    }
    
    private boolean isValidStep(int i, int j, int n, int m) {
        return i >= 0 && j >= 0 && i < n && j < m;
    }

    public boolean hasValidPath(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        visited = new boolean[n][m];

        return hasValidPath(0, 0, grid);
    }
}