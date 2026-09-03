class Solution {
    private boolean subsetSum(int[] nums, int sum) {
        int n = nums.length;

        boolean[][] dp = new boolean[n + 1][sum  + 1];

        for(int j = 1; j <= sum; j++) {
            dp[0][j] = false;
        }
        for(int i = 1; i <= n; i++) {
            dp[i][0] = true;
        }

        dp[0][0] = true;

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= sum; j++) {
                if(nums[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][sum];
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;

        for(int num : nums) {
            sum += num;
        }

        if(sum % 2 != 0) {
            return false;
        }

        return subsetSum(nums, sum/2);
    }
}