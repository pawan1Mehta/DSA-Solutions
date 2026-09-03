class Solution {

    private boolean isValidSquare(
        int startI, int startJ, int size, 
        int[][] prefRowSum, 
        int[][] prefColSum, 
        int[][] prefDiagonalSum, 
        int[][] prefRevDiagonalSum, 
        int n, int m
    ) {
        int endI = startI + size - 1;
        int endJ = startJ + size - 1;

        if(endI >= n || endJ >= m) {
            return false;
        }

        int sum = -1;

        for(int i = startI; i <= endI; i++) {
            int currSum = prefRowSum[i][endJ] - (startJ == 0 ? 0 : prefRowSum[i][startJ - 1]);
            if(sum == -1) {
                sum = currSum;
            }

            if(sum != currSum) {
                return false;
            }
        }

        for(int j = startJ; j <= endJ; j++) {
            int currSum = prefColSum[endI][j] - (startI == 0 ? 0 : prefColSum[startI - 1][j]);

            if(sum != currSum) {
                return false;
            }
        }

        int diagonalSum1 = prefDiagonalSum[endI][endJ] - ((startI == 0 || startJ == 0) ? 0 : prefDiagonalSum[startI - 1][startJ - 1]);
        if(diagonalSum1 != sum) {
            return false;
        }

        int diagonalSum2 = prefRevDiagonalSum[endI][startJ] - ((startI == 0 || endJ == m-1) ? 0 : prefRevDiagonalSum[startI - 1][endJ + 1]);
        if(diagonalSum2 != sum) {
            return false;
        }

        return true;
    }

    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefRowSum = getPrefixRowSum(grid);
        int[][] prefColSum = getPrefixColSum(grid);
        int[][] prefDiagonalSum = getPrefixDiagonalSum(grid);
        int[][] prefRevDiagonalSum = getRevPrefixDiagonalSum(grid);

        for(int size = Math.min(n, m); size >= 1; size--) {
            for(int i = 0; i <= n - size; i++) {
                for(int j = 0; j <= m - size; j++) {
                    if(isValidSquare(i, j, size, prefRowSum, prefColSum, prefDiagonalSum, prefRevDiagonalSum, n, m)) {
                        return size;
                    }
                }
            }
        }

        return 1;
    }

    private int[][] getPrefixRowSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefRowSum = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(j == 0) {
                    prefRowSum[i][j] = grid[i][j];
                } else {
                    prefRowSum[i][j] = prefRowSum[i][j - 1] + grid[i][j];
                }
            }
        }

        return prefRowSum;
    }

    private int[][] getPrefixColSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefColSum = new int[n][m];

        for(int j = 0; j < m; j++) {
            for(int i = 0; i < n; i++) {
                if(i == 0) {
                    prefColSum[i][j] = grid[i][j];
                } else {
                    prefColSum[i][j] = prefColSum[i - 1][j] + grid[i][j];
                }
            }
        }

        return prefColSum;
    }

    private int[][] getPrefixDiagonalSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefixDiagonalSum = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                prefixDiagonalSum[i][j] = grid[i][j];
                if(i > 0 && j > 0) {
                    prefixDiagonalSum[i][j] += prefixDiagonalSum[i - 1][j - 1];
                }
            }
        }

        return prefixDiagonalSum;
    }

    private int[][] getRevPrefixDiagonalSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] prefixDiagonalSum = new int[n][m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                prefixDiagonalSum[i][j] = grid[i][j];
                if(i > 0 && j < m - 1) {
                    prefixDiagonalSum[i][j] += prefixDiagonalSum[i - 1][j + 1];
                }
            }
        }

        return prefixDiagonalSum;
    }
}