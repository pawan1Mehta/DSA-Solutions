
class Solution {
    private int maximumAmountUtil(int l, int r, int turn, int[] coins, int[][][] memo) {
        if(l > r) {
            return 0;
        }    
        
        if(memo[l][r][turn] != -1) {
            return memo[l][r][turn];
        }
        
        if(turn == 0) {
            int opt1 = coins[l] + maximumAmountUtil(l + 1, r, 1, coins, memo);
            int opt2 = coins[r] + maximumAmountUtil(l, r - 1, 1, coins, memo);
            
            return memo[l][r][turn] = Math.max(opt1, opt2);
        } else {
            int opt1 = maximumAmountUtil(l + 1, r, 0, coins, memo);
            int opt2 = maximumAmountUtil(l, r - 1, 0, coins, memo);
            
            return memo[l][r][turn] = Math.min(opt1, opt2);
        }
    }
    
    public int maximumAmount(int arr[]) {
        int n = arr.length;
        
        int[][][] memo = new int[n][n][2];
    
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        
        return maximumAmountUtil(0, n - 1, 0, arr, memo);
    }
}