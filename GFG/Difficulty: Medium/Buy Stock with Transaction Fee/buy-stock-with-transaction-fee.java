class Solution {
    
    int[][] memo;
    
    private int maxProfitUtil(int i, int type, int k, int[] arr) {
        if(i == arr.length) {
            return 0;
        }
        
        if(memo[i][type] != -1) {
            return memo[i][type];
        }
        
        int opt1 = 0, opt2 = 0;
        
        if(type == 0) {
            opt1 = maxProfitUtil(i + 1, 1, k, arr) - arr[i] - k;
        } else {
            opt1 = arr[i] + maxProfitUtil(i + 1, 0, k, arr);
        }
        
        opt2 = maxProfitUtil(i + 1, type, k, arr);
        
        return memo[i][type] = Math.max(opt1, opt2);
    }
    
    public int maxProfit(int arr[], int k) {
        int n = arr.length;
        
        memo = new int[n][2];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return maxProfitUtil(0, 0, k, arr);
    }
}