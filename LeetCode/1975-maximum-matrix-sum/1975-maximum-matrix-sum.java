class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        long absSum = 0;
        long minNum = Long.MAX_VALUE;
        long negativeNumCount = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                absSum += Math.abs(matrix[i][j]);
                minNum = Math.min(minNum, Math.abs(matrix[i][j]));
                if(matrix[i][j] < 0) {
                    negativeNumCount++;
                } 
            }
        }

        if(negativeNumCount%2 == 0) {
            return absSum;
        }

        return absSum - (2 * minNum);
    }
}


