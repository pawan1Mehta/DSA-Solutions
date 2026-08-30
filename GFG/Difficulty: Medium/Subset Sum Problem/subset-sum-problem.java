class Solution {
    
    static Boolean[][] memo;
    
    static Boolean isSubsetSumUtil(int i, int sum, int[] arr) {
        if(sum == 0) {
            return true;
        }
        
        if(i == arr.length) {
            return false;
        }
        
        if(memo[i][sum] != null) {
            return memo[i][sum];
        }
        
        Boolean opt1 = false, opt2 = false;
        
        if(arr[i] <= sum) {
            opt1 = isSubsetSumUtil(i + 1, sum - arr[i], arr);
        }
        
        opt2 = isSubsetSumUtil(i + 1, sum, arr);
        
        return memo[i][sum] = (opt1 || opt2);
    }

    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        
        memo = new Boolean[n + 1][sum + 1];
        
        for(int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], null);
        }
        
        return isSubsetSumUtil(0, sum, arr);
    }
}