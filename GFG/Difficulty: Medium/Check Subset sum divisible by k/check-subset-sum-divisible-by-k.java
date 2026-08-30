class Solution {
    public boolean divisibleByK(int[] arr, int k) {
        int n = arr.length;
        
        boolean[] dp = new boolean[k];
        
        for(int i = 0; i < n; i++) {
            if(dp[0]) {
                return true;
            }
        
            boolean[] temp = new boolean[k];
            
            for(int j = 0; j < k; j++) {
                if(dp[j] && !dp[(j + arr[i]) % k]) {
                    temp[(j + arr[i]) % k] = true; 
                }    
            }
            
            for(int j = 0; j < k; j++) {
                if(temp[j]) {
                    dp[j] = true;
                }
            }
            
            dp[arr[i] % k] = true;
        }
        
        if(dp[0]) {
            return true;
        }
        
        return false;
    }
}