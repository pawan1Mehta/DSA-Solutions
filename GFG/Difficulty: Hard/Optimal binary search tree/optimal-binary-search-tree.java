class Solution {
    private int sum(int freq[], int i, int j) {
        int s = 0;
        for (int k = i; k <= j; k++)
            s += freq[k];
        return s;
    }
    
    public int minCost(int keys[], int freq[]) {
        int n = keys.length;

        int dp[][] = new int[n][n];

        for (int i = 0; i < n; i++)
            dp[i][i] = freq[i];

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;

                int fsum = sum(freq, i, j);
    
                for (int r = i; r <= j; r++) {
                    int c = ((r > i) ? dp[i][r - 1] : 0) + ((r < j) ? dp[r + 1][j] : 0) + fsum;

                    if (c < dp[i][j])
                        dp[i][j] = c;
                }
            }
        }

        return dp[0][n - 1];
    }
}
