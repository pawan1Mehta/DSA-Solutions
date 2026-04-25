class Solution {
    
    private int[] memo;
    
    private int derangeCountUtil(int n) {
        if(n == 1) {
            return 0;
        }
        if(n == 2) {
            return 1;
        }
        
        if(memo[n] != -1) {
            return memo[n];
        }
        
        return memo[n] = (n - 1) * (derangeCountUtil(n - 2) + derangeCountUtil(n - 1));
    }
    
    public int derangeCount(int n) {
        memo = new int[n + 1];
        
        Arrays.fill(memo, -1);
        
        return derangeCountUtil(n);
    }
};