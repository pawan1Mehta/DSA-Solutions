class Solution {
    public int maxSumIS(int nums[]) {
        int n = nums.length;
        
        int[] len = new int[n];
        int[] sum = new int[n];
        
        for(int i = 0; i < n; i++) {
            len[i] = 1;
            sum[i] = nums[i];
        }
        
        int maxSum = nums[0];
        
        for(int i = 1; i < n; i++) {
            int j = i - 1;
            while(j >= 0) {
                if(nums[i] > nums[j]) {
                    if((sum[j] + nums[i]) > sum[i]) {
                        sum[i] = sum[j] + nums[i];
                        len[i] = len[j] + 1;
                    }
                }
                j--;
            }
            
            maxSum = Math.max(maxSum, sum[i]);
        }
        
        return maxSum;
    }
}
/*

    11 13 5 5 7  3  8  15  13  8  1  9
                               |
    1  2  1 1 2  1  3  4   4   2  1  1 
    11 24 5 5 12 3  15 23  21  11  1  9
    
    
 
    
    
    
    
    



*/