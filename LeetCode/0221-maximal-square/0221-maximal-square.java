class Solution {
    public int maximalSquare(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] mat = new int[n][m];

        int maxVal = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                mat[i][j] = matrix[i][j] - '0';
                maxVal = Math.max(maxVal, mat[i][j]);
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(mat[i][j] == 1) {
                    mat[i][j] += Math.min(mat[i - 1][j - 1], Math.min(mat[i][j - 1], mat[i - 1][j]));
                    maxVal = Math.max(maxVal, mat[i][j]);
                }
            }
        }

        if(maxVal <= 1) {
            return maxVal;
        }

        return (int) Math.pow(maxVal, 2);
    }
}