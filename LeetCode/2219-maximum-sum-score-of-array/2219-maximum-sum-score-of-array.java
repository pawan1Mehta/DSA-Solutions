class Solution {
    public long maximumSumScore(int[] nums) {
        int n = nums.length;

        long[] prefixSum = new long[n];
        long[] suffixSum = new long[n];

        for(int i = 0; i < n; i++) {
            if(i == 0) {
                prefixSum[i] = nums[i];
            } else {
                prefixSum[i] = prefixSum[i - 1] + nums[i];
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            if(i == n - 1) {
                suffixSum[i] = nums[i];
            } else {
                suffixSum[i] = suffixSum[i + 1] + nums[i];
            }
        }

        long maxSum = Integer.MIN_VALUE;
        
        for(int i = 0; i < n; i++) {
            maxSum = Math.max(maxSum, Math.max(prefixSum[i], suffixSum[i]));
        }

        return maxSum;
    }
}