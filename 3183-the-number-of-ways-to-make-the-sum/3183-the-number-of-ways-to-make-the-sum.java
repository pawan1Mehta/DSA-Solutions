class Solution {
    private static final int MOD = 1_000_000_007;

    int[][][] memo;
    
    private int numberOfWaysUtil(int n, int coin4Count, int[] coins, int start) {
        if(n == 0) {
            return 1;
        }

        if(memo[n][coin4Count][start] != -1) {
            return memo[n][coin4Count][start];
        }

        int ways = 0;

        for(int i = start; i < 4; i++) {
            int coin = coins[i];
            if(coin <= n) {
                if(coin == 4) {
                    if(coin4Count > 0) {
                        ways = (ways + numberOfWaysUtil(n - coin, coin4Count - 1, coins, i)) % MOD;
                    }
                } else {
                    ways = (ways + numberOfWaysUtil(n - coin, coin4Count, coins, i)) % MOD;
                }
            }
        }

        return memo[n][coin4Count][start] = ways;
    }

    public int numberOfWays(int n) {
        int[] coins = new int[]{1, 2, 4, 6};

        memo = new int[n + 1][3][4];
        for(int i = 0; i <= n; i++) {
            for(int j = 0; j < 3; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }

        return numberOfWaysUtil(n, 2, coins, 0);
    }
}