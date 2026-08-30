class Solution {
    
    int[] memo;
    
    public int optimalKeysUtil(int n) {
        if(n <= 6) {
            return n;
        }
        
        if(memo[n] != -1) {
            return memo[n];
        }
        
        int max = 0;
        
        for(int num = n - 3; num >= 1; num--) {
            int as = (n - num - 1) * optimalKeysUtil(num);
            if(as > max) {
                max = as;
            }
        }
        
        return memo[n] = max;
    }
    
    public int optimalKeys(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);
        
        return optimalKeysUtil(n);
    } 
}