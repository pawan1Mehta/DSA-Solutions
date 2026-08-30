class Solution {
    private int[][] memo;

    private int minRemovalsUtil(int i, int target, int[] nums) {
        if(i == nums.length) {
            if(target == 0) {
                return 0;
            }
            return Integer.MAX_VALUE;
        }

        if(memo[i][target] != -1) {
            return memo[i][target];
        }

        int opt1 = minRemovalsUtil(i + 1, target ^ nums[i], nums);
        int opt2 = minRemovalsUtil(i + 1, target, nums);

        if(opt2 != Integer.MAX_VALUE) {
            opt2 += 1;
        }

        return memo[i][target] = Math.min(opt1, opt2);
    }

    public int minRemovals(int[] nums, int target) {
        int n = nums.length;

        memo = new int[n][1_000_01];
        for(int i = 0; i < n; i++) {
            Arrays.fill(memo[i], -1);
        }

        int ans = minRemovalsUtil(0, target, nums);
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}