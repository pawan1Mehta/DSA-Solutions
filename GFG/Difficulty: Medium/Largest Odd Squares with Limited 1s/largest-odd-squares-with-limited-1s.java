class Solution {
    ArrayList<Integer> largestSquare(int[][] mat, int[][] queries, int k) {
        int n = mat.length;
        int m = mat[0].length;
        
        int[][] prefSum = new int[n + 1][m + 1];
        
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                prefSum[i][j] = prefSum[i - 1][j] + prefSum[i][j - 1] + mat[i - 1][j - 1] - prefSum[i - 1][j - 1];
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        
        for(int[] query : queries) {
            int row = query[0] + 1;
            int col = query[1] + 1;
            
            int maxOddSize = -1;
            
            int ones = prefSum[row][col]
                 - prefSum[row - 1][col]
                 - prefSum[row][col - 1]
                 + prefSum[row - 1][col - 1];
        
            if (ones <= k) maxOddSize = 1;
            
            for(int num = 1, size = 3; num < 500; num++, size += 2) {
                int r1 = row - num;
                int c1 = col - num;
                
                int r2 = row + num;
                int c2 = col + num;
                
                if(r1 < 1 || c1 < 1 || r2 > n || c2 > m) break;
                
                int sum = prefSum[r2][c2]
                - prefSum[r1 - 1][c2]
                - prefSum[r2][c1 - 1]
                + prefSum[r1 - 1][c1 - 1];
                
                if(sum <= k) {
                    maxOddSize = Math.max(maxOddSize, size);
                }
            }
            
            res.add(maxOddSize);
        }
        
        return res;
    }
}