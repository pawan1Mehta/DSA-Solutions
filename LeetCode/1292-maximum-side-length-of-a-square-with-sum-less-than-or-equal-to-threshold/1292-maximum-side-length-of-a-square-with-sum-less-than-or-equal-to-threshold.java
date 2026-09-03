class Solution {
    
    private boolean isValidSideSquarePresent(int[][] prefixSum, int side, int threshold) {
        int n = prefixSum.length;
        int m = prefixSum[0].length;

        for(int x1 = 1; x1 <= (n - side); x1++) {
            for(int y1 = 1; y1 <= (m - side); y1++) {
                int x2 = x1 + side - 1;
                int y2 = y1 + side - 1;

                int currSqrSum = prefixSum[x2][y2] - prefixSum[x1 - 1][y2] - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];

                if(currSqrSum <= threshold) {
                    return true;
                }
            }
        }

        return false;
    }

    public int maxSideLength(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;

        int[][] prefixSum = new int[n + 1][m + 1];

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                prefixSum[i][j] = mat[i - 1][j - 1] + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        int maxSideLen = 0;

        int low = 1, high = Math.min(n, m);

        while(low <= high) {
            int mid = (low + high)/2;
            if(isValidSideSquarePresent(prefixSum, mid, threshold)) {
                maxSideLen = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return maxSideLen;
    }
}