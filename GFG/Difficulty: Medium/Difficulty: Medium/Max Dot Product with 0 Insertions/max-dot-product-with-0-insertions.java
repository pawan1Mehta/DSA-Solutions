class Solution {
    
    int[][][] memo;
    
    private int maxDotProductUtil(int i, int j, int rem, int[] a, int[] b) {
        if(i == a.length || j == b.length) {
            return 0;
        }
        
        if(memo[i][j][rem] != -1) {
            return memo[i][j][rem];
        }
        
        if(rem > 0) {
            int opt1 = maxDotProductUtil(i + 1, j, rem - 1, a, b);
            int opt2 = (a[i] * b[j]) + maxDotProductUtil(i + 1, j + 1, rem, a, b);
            return memo[i][j][rem] = Math.max(opt1, opt2);
        } else {
            return memo[i][j][rem] = ((a[i] * b[j]) + maxDotProductUtil(i + 1, j + 1, rem, a, b));
        }
    }
    
    public int maxDotProduct(int[] a, int[] b) {
        int n = a.length;
        int m = b.length;
        
        int rem = n - m;
        
        memo = new int[n][m][rem + 1];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        
        return maxDotProductUtil(0, 0, rem, a, b);
    }
}