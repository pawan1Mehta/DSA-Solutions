class Solution {
    public int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        
        int maxSum = 0;
        
        int currSum = 0;
        for(int i = 0; i < k; i++) {
            currSum += arr[i];
        }
        
        maxSum = currSum;
        
        for(int i = 0, j = k; j < n; i++, j++) {
            currSum -= arr[i];
            currSum += arr[j];
            
            maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
}