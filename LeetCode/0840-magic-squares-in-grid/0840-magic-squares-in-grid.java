class Solution {
    private boolean isValidGrid(int[][] grid, int startRow, int startColumn, int endRow, int endColumn, int n, int m) {
        if(!(endRow < n && endColumn < m)) {
            return false;
        }

        int sum = -1;

        Set<Integer> st = new HashSet<>();

        for(int i = startRow; i <= endRow; i++) {
            int rowSum = 0;
            for(int j = startColumn; j <= endColumn; j++) {
                if(grid[i][j] < 1 || grid[i][j] > 9) {
                    return false;
                }

                rowSum += grid[i][j];
                st.add(grid[i][j]);
            }

            if(sum == -1) {
                sum = rowSum;
            }

            if(sum != rowSum) {
                return false;
            }
        }

        for(int i = startColumn; i <= endColumn; i++) {
            int columnSum = 0;
            for(int j = startRow; j <= endRow; j++) {
                columnSum += grid[j][i];
            }
        
            if(sum != columnSum) {
                return false;
            }
        }

        int diagonalSum1 = grid[startRow][startColumn] + grid[startRow + 1][startColumn + 1] + grid[startRow + 2][startColumn + 2];
        int diagonalSum2 = grid[startRow][endColumn] + grid[startRow + 1][endColumn - 1] + grid[startRow + 2][endColumn - 2];

        if(sum != diagonalSum1 || sum != diagonalSum2 || st.size() != 9) {
            return false;
        } 

        return true;
    }

    public int numMagicSquaresInside(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int count = 0;

        for(int row = 1; row < n - 1; row++) {
            for(int column = 1; column < m - 1; column++) {
                int startRow = row - 1;
                int startColumn = column - 1;
                int endRow = row + 1;
                int endColumn = column + 1;

                if(isValidGrid(grid, startRow, startColumn, endRow, endColumn, n, m)) {
                    count++;
                }
            }
        }

        return count;
    }
}