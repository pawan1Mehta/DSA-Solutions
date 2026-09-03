class Solution {
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        int mat1Rows = mat1.length;
        int mat1Cols = mat1[0].length;

        int mat2Rows = mat2.length;
        int mat2Cols = mat2[0].length;

        int[][] res = new int[mat1Rows][mat2Cols];

        for(int row = 0; row < mat1Rows; row++) {
            for(int j = 0; j < mat2Cols; j++) {
                int total = 0;
                for(int i = 0; i < mat1Cols; i++) {
                    total += mat1[row][i] * mat2[i][j];
                }
                res[row][j] = total;
            }
        }

        return res;
    }
}