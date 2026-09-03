class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] matrix = new int[n][n];

        int r1, c1, r2, c2;
        for(int[] query : queries) {
            r1 = query[0]; c1 = query[1];
            r2 = query[2]; c2 = query[3];

            for(int i = r1; i <= r2; i++) {
                matrix[i][c1]++;
            }

            if(c2 + 1 < n) {
                for(int i = r1; i <= r2; i++) {
                    matrix[i][c2 + 1]--;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 1; j < n; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        return matrix;
    }
}